package com.project.payroll.api.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "funcionarios")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CPF
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToOne(mappedBy = "funcionario")
    private Usuario usuario;

    @Column
    private String nome;

    @Column
    private LocalDateTime dataNascimento;

    @Column
    private LocalDateTime dataDesligamento;

    @Column
    private Character sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Column
    private TipoContrato tipoContrato;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salario_id", referencedColumnName = "id")
    private Salario salarioMensal;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private final Set<Telefone> telefones = new HashSet<>();

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private final Set<Email> emails = new HashSet<>();

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private final Set<FolhaPonto> folhaPontos = new HashSet<>();

    public Funcionario(){
    }

    public Funcionario(Long id,
                       String cpf,
                       Empresa empresa,
                       String nome,
                       LocalDateTime dataNascimento,
                       LocalDateTime dataDesligamento,
                       Character sexo,
                       Endereco endereco,
                       Cargo cargo, TipoContrato tipoContrato, Salario salarioMensal) {
        this.id = id;
        this.cpf = cpf;
        this.empresa = empresa;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataDesligamento = dataDesligamento;
        this.sexo = sexo;
        this.endereco = endereco;
        this.cargo = cargo;
        this.tipoContrato = tipoContrato;
        this.salarioMensal = salarioMensal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(LocalDateTime dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Salario getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(Salario salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
