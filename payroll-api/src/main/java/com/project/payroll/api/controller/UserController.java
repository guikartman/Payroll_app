package com.project.payroll.api.controller;

import com.project.payroll.api.dto.UsuarioDTO;
import com.project.payroll.api.dto.UsuarioNewDTO;
import com.project.payroll.api.entities.Funcionario;
import com.project.payroll.api.entities.Usuario;
import com.project.payroll.api.services.FuncionarioService;
import com.project.payroll.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

	private UserService service;

	private FuncionarioService funcionarioService;

	@Autowired
	public UserController(UserService service, FuncionarioService funcionarioService) {
		this.service = service;
		this.funcionarioService = funcionarioService;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> find(@PathVariable Long id) {
		UsuarioDTO obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{email}", method=RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> find(@RequestParam(value="value") String email) {
		UsuarioDTO obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDto) {
		Funcionario funcionario = funcionarioService.findUsuarioByCpf(objDto.getCpfFuncionario());
		Usuario obj = service.fromDTO(objDto,funcionario);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
}
