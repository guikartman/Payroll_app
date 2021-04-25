package com.project.payroll.api.repositories;

import com.project.payroll.api.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    public Optional<Funcionario> findByCpf(String cpf);
}
