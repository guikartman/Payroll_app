package com.project.payroll.api.services;

import com.project.payroll.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository repository;

	public void instantiateTestDatabase() {
		
//		Usuario usu1 = new Usuario(null, "gui@gmail.com", pe.encode("meutime123"));
//
//		repository.save(usu1);
	}
	
	
}
