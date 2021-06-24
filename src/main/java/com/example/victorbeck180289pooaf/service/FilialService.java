package com.example.victorbeck180289pooaf.service;

import java.util.List;

import com.example.victorbeck180289pooaf.entity.Filial;
import com.example.victorbeck180289pooaf.repository.FilialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilialService {
    @Autowired
    FilialRepository repository;

    public List<Filial> getFiliais(){
        return repository.findAll();
    }
    
    public void salvar(Filial f)
    {
        if(f.getEndereco().equals(null)||f.getTelefone().equals(null))
        return;

        repository.save(f);
    }

    public void remover(Filial f){
        repository.delete(f);
    }

    public Filial getFilialById(int id) {
		return repository.findById(id).get();
    }
    
}