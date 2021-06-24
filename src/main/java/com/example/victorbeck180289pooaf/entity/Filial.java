package com.example.victorbeck180289pooaf.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


//Decidi que fosse uma franquia de barbearias, assim, cada agendamento deve ser linkado a uma filial.

@Entity
public class Filial implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String endereco;
    private String telefone;
    @OneToMany
    private List<Agendamento> agendamentos;
    @ManyToMany(targetEntity = Barbeiro.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "BARBEIRO_FILIAL", 
    joinColumns = @JoinColumn(name = "FILIAL_ID"), 
    inverseJoinColumns = @JoinColumn(name = "BARBEIRO_ID"))
    private List<Barbeiro> barbeiros;

    @Override
    public String toString() {
        return this.endereco;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    public void setBarbeiros(List<Barbeiro> barbeiros) {
        this.barbeiros = barbeiros;
    }
    
}