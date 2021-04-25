package com.project.payroll.api.services;

import com.project.payroll.api.dto.UsuarioDTO;
import com.project.payroll.api.dto.UsuarioNewDTO;
import com.project.payroll.api.entities.Funcionario;
import com.project.payroll.api.entities.Usuario;
import com.project.payroll.api.repositories.UserRepository;
import com.project.payroll.api.security.UserSS;
import com.project.payroll.api.services.exception.DatabaseException;
import com.project.payroll.api.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	
	public UsuarioDTO find(Long id) {

		UserSS user = UserService.authenticated();
//		if (user == null && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Usuario obj = ((Optional<Usuario>)repo.findById(id)).get();
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return new UsuarioDTO(obj);
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
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

	public UsuarioDTO findByEmail(String email) {

		UserSS user = UserService.authenticated();
		
		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return new UsuarioDTO(obj);
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getEmail(),null,objDto.getTipo());
	}

	public Usuario fromDTO(UsuarioNewDTO objDto, Funcionario funcionario) {
		Usuario cli = new Usuario(objDto.getEmail(), pe.encode(objDto.getSenha()), objDto.getTipo(), funcionario);
	
		return cli;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
	}
}
