package com.project.payroll.api.controller;

import com.project.payroll.api.dto.EmpresaNewDTO;
import com.project.payroll.api.dto.UsuarioDTO;
import com.project.payroll.api.entities.Empresa;
import com.project.payroll.api.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

    private EmpresaService service;

    @Autowired
    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> criarNovaEmpresa(@RequestBody EmpresaNewDTO novaEmpresa) {
        Empresa empresa = service.fromDTO(novaEmpresa);
        empresa = service.salvarEmpresa(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> recuperarUsuariosByEmpresaId(@PathVariable Long id) {
        Empresa empresa = service.findById(id);
        List<UsuarioDTO> usuarios = service.listarUsuariosByEmpresa(empresa);
        return ResponseEntity.ok(usuarios);
    }
}
