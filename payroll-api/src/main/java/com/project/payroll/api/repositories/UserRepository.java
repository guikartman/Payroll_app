package com.project.payroll.api.repositories;

import com.project.payroll.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly=true)
	Usuario findByEmail(String email);
}
