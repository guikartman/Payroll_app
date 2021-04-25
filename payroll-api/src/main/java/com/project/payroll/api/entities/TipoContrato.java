package com.project.payroll.api.entities;

public enum TipoContrato {
    PJ("PJ"),
    CLT("CLT");

    String descricao;

    TipoContrato(String descricao) {
        this.descricao = descricao;
    }
}
