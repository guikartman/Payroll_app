package com.project.payroll.api.services;

import com.project.payroll.api.entities.Usuario;
import com.project.payroll.api.repositories.UserRepository;
import com.project.payroll.api.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha());
	}
	
}
