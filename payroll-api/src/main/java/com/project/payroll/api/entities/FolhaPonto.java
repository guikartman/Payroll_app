package com.project.payroll.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "folha_pontos")
public class FolhaPonto implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PontoUsuarioPK pontoUsuarioPK;

    @MapsId("usuarioId")
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column
    private LocalTime horaEntrada;

    @Column
    private LocalTime horaInicioAlmoco;

    @Column
    private LocalTime horaFimAlmoco;

    @Column
    private LocalTime horaSaida;

    public FolhaPonto(){
    }

    public FolhaPonto(PontoUsuarioPK pontoUsuarioPK, Usuario usuario, LocalTime horaEntrada, LocalTime horaInicioAlmoco, LocalTime horaFimAlmoco, LocalTime horaSaida) {
        this.pontoUsuarioPK = pontoUsuarioPK;
        this.usuario = usuario;
        this.horaEntrada = horaEntrada;
        this.horaInicioAlmoco = horaInicioAlmoco;
        this.horaFimAlmoco = horaFimAlmoco;
        this.horaSaida = horaSaida;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public PontoUsuarioPK getPontoUsuarioPK() {
        return pontoUsuarioPK;
    }

    public void setPontoUsuarioPK(PontoUsuarioPK pontoUsuarioPK) {
        this.pontoUsuarioPK = pontoUsuarioPK;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraInicioAlmoco() {
        return horaInicioAlmoco;
    }

    public void setHoraInicioAlmoco(LocalTime horaInicioAlmoco) {
        this.horaInicioAlmoco = horaInicioAlmoco;
    }

    public LocalTime getHoraFimAlmoco() {
        return horaFimAlmoco;
    }

    public void setHoraFimAlmoco(LocalTime horaFimAlmoco) {
        this.horaFimAlmoco = horaFimAlmoco;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolhaPonto that = (FolhaPonto) o;
        return Objects.equals(pontoUsuarioPK, that.pontoUsuarioPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pontoUsuarioPK);
    }
}
