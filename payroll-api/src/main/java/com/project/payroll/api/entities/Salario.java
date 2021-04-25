package com.project.payroll.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "salarios")
public class Salario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal salarioMensal;

    @Column
    private LocalDateTime dataAtualizacaoSalario;

    @OneToOne(mappedBy = "salarioMensal")
    private Funcionario funcionario;

    public Salario(){
    }

    public Salario(Long id, BigDecimal salarioMensal, LocalDateTime dataAtualizacaoSalario, Funcionario funcionario) {
        this.id = id;
        this.salarioMensal = salarioMensal;
        this.dataAtualizacaoSalario = dataAtualizacaoSalario;
        this.funcionario = funcionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(BigDecimal salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public LocalDateTime getDataAtualizacaoSalario() {
        return dataAtualizacaoSalario;
    }

    public void setDataAtualizacaoSalario(LocalDateTime dataAtualizacaoSalario) {
        this.dataAtualizacaoSalario = dataAtualizacaoSalario;
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
        Salario salario = (Salario) o;
        return Objects.equals(id, salario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
