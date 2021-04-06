package com.project.payroll.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.payroll.api.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly=true)
	Usuario findByEmail(String email);
}
