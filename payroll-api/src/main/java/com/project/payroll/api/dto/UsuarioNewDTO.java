package com.project.payroll.api.dto;

import com.project.payroll.api.entities.Empresa;
import com.project.payroll.api.entities.TipoUsuario;
import com.project.payroll.api.entities.Usuario;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UsuarioNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotNull(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Preenchimento obrigatório")
    private String senha;

    @NotNull(message = "O tipo de usuario é obrigatorio, preencha entre Administrador ou Funcionario")
    private TipoUsuario tipo;

    @NotNull(message = "O codigo da empresa deve estar presente")
    private Long codigoEmpresa;

    public UsuarioNewDTO() {
    }

    public UsuarioNewDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.tipo = usuario.getTipo();
        this.codigoEmpresa = usuario.getEmpresa().getId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Long codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
}
