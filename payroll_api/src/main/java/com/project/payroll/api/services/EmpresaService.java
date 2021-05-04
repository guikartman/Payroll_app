package com.project.payroll.api.services;

import com.project.payroll.api.dto.EmpresaNewDTO;
import com.project.payroll.api.dto.UsuarioDTO;
import com.project.payroll.api.entities.Empresa;
import com.project.payroll.api.repositories.EmpresaRepository;
import com.project.payroll.api.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private EmpresaRepository repository;

    @Autowired
    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public Empresa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa com o id informado não pode ser encontrada"));
    }

    public Empresa salvarEmpresa(Empresa empresa) {
        return repository.save(empresa);
    }

    public List<UsuarioDTO> listarUsuariosByEmpresa(Empresa empresa) {
        return empresa.getUsuarios().stream().map(usuario -> {
            return new UsuarioDTO(usuario);
        }).collect(Collectors.toList());
    }

    public Empresa fromDTO(EmpresaNewDTO objDto) {
        return new Empresa(objDto.getCnpj(),objDto.getRazaoSocial());
    }
}
