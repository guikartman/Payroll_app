package com.project.payroll.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.payroll.api.entities.FolhaPonto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

public class FolhaPontoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime entrada;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime inicioPausa;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime fimPausa;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime saida;

    @NotNull(message = "O ponto deve ter 1 data")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private String dataPonto;

    public FolhaPontoDTO(LocalTime entrada, LocalTime inicioPausa, LocalTime fimPausa, LocalTime saida, String dataPonto) {
        this.entrada = entrada;
        this.inicioPausa = inicioPausa;
        this.fimPausa = fimPausa;
        this.saida = saida;
        this.dataPonto = dataPonto;
    }

    public FolhaPontoDTO(FolhaPonto folhaPonto) {
        this.entrada = folhaPonto.getHoraEntrada();
        this.inicioPausa = folhaPonto.getHoraInicioAlmoco();
        this.fimPausa = folhaPonto.getHoraFimAlmoco();
        this.saida = folhaPonto.getHoraSaida();
        this.dataPonto = folhaPonto.getPontoUsuarioPK().getDataPonto().toString();
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getInicioPausa() {
        return inicioPausa;
    }

    public void setInicioPausa(LocalTime inicioPausa) {
        this.inicioPausa = inicioPausa;
    }

    public LocalTime getFimPausa() {
        return fimPausa;
    }

    public void setFimPausa(LocalTime fimPausa) {
        this.fimPausa = fimPausa;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }

    public String getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(String dataPonto) {
        this.dataPonto = dataPonto;
    }

}
