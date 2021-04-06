package com.project.payroll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.payroll.api.entities.Usuario;
import com.project.payroll.api.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository repository;

	public void instantiateTestDatabase() {
		
		Usuario usu1 = new Usuario(null, "Guilherme Dantas", "gui@gmail.com", pe.encode("meutime123"));
		
		repository.save(usu1);
	}
	
	
}
