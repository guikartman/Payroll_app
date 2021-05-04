package com.project.payroll.api.entities;

public enum TipoUsuario {

    ADMINISTRADOR("ADMINISTRADOR"),
    FUNCIONARIO("FUNCIONARIO");

    String tipo;

    private TipoUsuario(String tipo) {
        this.tipo = tipo;
    }
}
