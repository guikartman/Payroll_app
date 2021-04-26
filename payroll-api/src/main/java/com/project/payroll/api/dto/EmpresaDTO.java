package com.project.payroll.api.dto;

import com.project.payroll.api.entities.Empresa;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;

public class EmpresaDTO {

    private Long id;
    private String cnpj;
    private String razaoSocial;

    public EmpresaDTO(Long id, String cnpj, String razaoSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
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
