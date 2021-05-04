package com.project.payroll.api.dto;

import com.project.payroll.api.entities.Empresa;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;

public class EmpresaDTO {

    private Long id;
    private String cnpj;
    private String razaoSocial;

    public EmpresaDTO(Empresa empresa) {
        this.id = empresa.getId();
        this.cnpj = empresa.getCnpj();
        this.razaoSocial = empresa.getRazaoSocial();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
