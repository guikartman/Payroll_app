package com.project.payroll.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.payroll.api.dto.UsuarioDTO;
import com.project.payroll.api.dto.UsuarioNewDTO;
import com.project.payroll.api.entities.Usuario;
import com.project.payroll.api.repositories.UserRepository;
import com.project.payroll.api.security.UserSS;
import com.project.payroll.api.services.exception.DatabaseException;
import com.project.payroll.api.services.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public Usuario find(Long id) {

		UserSS user = UserService.authenticated();
//		if (user == null && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Usuario obj = ((Optional<Usuario>)repo.findById(id)).get();
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario findByEmail(String email) {

		UserSS user = UserService.authenticated();
		
		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario cli = new Usuario(null, objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
	
		return cli;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
