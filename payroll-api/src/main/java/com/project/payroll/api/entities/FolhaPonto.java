package com.project.payroll.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "folha_pontos")
public class FolhaPonto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalTime horaEntrada;

    @Column
    private LocalTime horaInicioAlmoco;

    @Column
    private LocalTime horaFimAlmoco;

    @Column
    private LocalTime horaSaida;

    @Column
    private LocalDate dataPonto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public FolhaPonto(){
    }

    public FolhaPonto(LocalDate dataPonto, Usuario usuario) {
        this.dataPonto = dataPonto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
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

    public LocalDate getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(LocalDate dataPonto) {
        this.dataPonto = dataPonto;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
