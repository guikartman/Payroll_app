package com.project.payroll.api.dto;

import com.project.payroll.api.entities.TipoUsuario;
import com.project.payroll.api.entities.Usuario;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private TipoUsuario tipo;
	private String funcionarioCpf;

	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		email = obj.getEmail();
		tipo = obj.getTipo();
		funcionarioCpf = obj.getFuncionario().getCpf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getFuncionarioCpf() {
		return funcionarioCpf;
	}

	public void setFuncionarioCpf(String funcionarioCpf) {
		this.funcionarioCpf = funcionarioCpf;
	}
}

