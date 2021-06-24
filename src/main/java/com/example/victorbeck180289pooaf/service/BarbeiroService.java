package com.example.victorbeck180289pooaf.service;

import java.util.List;

import com.example.victorbeck180289pooaf.entity.Barbeiro;
import com.example.victorbeck180289pooaf.entity.Filial;
import com.example.victorbeck180289pooaf.repository.BarbeiroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService {
    @Autowired
    private BarbeiroRepository repository;

    public List<Barbeiro> getBarbeiros(){
        return repository.findAll();
    }
    
    public void salvar(Barbeiro b)
    {
        if(b.getNome().equals(null)||b.getSalario()<0)
        return;

        repository.save(b);
    }

    public void remover(Barbeiro b){
        repository.delete(b);
    }

    public Barbeiro getBarbeiroById(int id) {
		return repository.findById(id).get();
    }

}