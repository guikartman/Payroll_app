package com.project.payroll.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "folha_pontos")
public class FolhaPonto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime horaEntrada;

    @Column
    private LocalDateTime horaInicioAlmoco;

    @Column
    private LocalDateTime horaFimAlmoco;

    @Column
    private LocalDateTime horaSaida;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public FolhaPonto(){
    }

    public FolhaPonto(Long id, LocalDateTime horaEntrada, LocalDateTime horaInicioAlmoco, LocalDateTime horaFimAlmoco, LocalDateTime horaSaida, Funcionario funcionario) {
        this.id = id;
        this.horaEntrada = horaEntrada;
        this.horaInicioAlmoco = horaInicioAlmoco;
        this.horaFimAlmoco = horaFimAlmoco;
        this.horaSaida = horaSaida;
        this.funcionario = funcionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraInicioAlmoco() {
        return horaInicioAlmoco;
    }

    public void setHoraInicioAlmoco(LocalDateTime horaInicioAlmoco) {
        this.horaInicioAlmoco = horaInicioAlmoco;
    }

    public LocalDateTime getHoraFimAlmoco() {
        return horaFimAlmoco;
    }

    public void setHoraFimAlmoco(LocalDateTime horaFimAlmoco) {
        this.horaFimAlmoco = horaFimAlmoco;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
