package com.project.payroll.api.dto;

import com.project.payroll.api.entities.Empresa;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EmpresaNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O campo CNPJ é obrigatorio")
    @CNPJ(message = "O CNPJ deve ser valido")
    private String cnpj;

    @NotNull(message = "A empresa deve ter uma Razão Social")
    private String razaoSocial;

    public EmpresaNewDTO(Empresa empresa) {
        this.cnpj = empresa.getCnpj();
        this.razaoSocial = empresa.getRazaoSocial();
    }

    public EmpresaNewDTO(){
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
