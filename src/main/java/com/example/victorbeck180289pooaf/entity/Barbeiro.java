package com.example.victorbeck180289pooaf.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
public class Barbeiro implements Serializable {
     /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private double salario;
    @ManyToMany
    @JoinTable(name = "BARBEIRO_FILIAL", 
    joinColumns = @JoinColumn(name = "BARBEIRO_ID"), 
    inverseJoinColumns = @JoinColumn(name = "FILIAL_ID"))
    private List<Filial> filiais;
    @OneToMany
    private List<Agendamento> agendamentos;

    @Override
    public String toString() {
        return this.nome;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}