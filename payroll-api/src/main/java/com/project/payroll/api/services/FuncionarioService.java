package com.project.payroll.api.services;

import com.project.payroll.api.entities.Funcionario;
import com.project.payroll.api.repositories.FuncionarioRepository;
import com.project.payroll.api.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository repository;

    @Autowired
    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario findUsuarioByCpf(String cpf) {

        Optional<Funcionario> funcionario = repository.findByCpf(cpf);
        return funcionario.orElseThrow(() -> new ResourceNotFoundException("Usuario com esse CPF não pode ser encontrado"));
    }
}
