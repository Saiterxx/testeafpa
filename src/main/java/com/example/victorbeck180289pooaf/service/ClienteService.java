package com.example.victorbeck180289pooaf.service;

import java.util.List;

import com.example.victorbeck180289pooaf.entity.Cliente;
import com.example.victorbeck180289pooaf.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;
    
    public List<Cliente> getClientes(){
        return repository.findAll();
    }
    
    public void salvar(Cliente c)
    {
        if(c.getNome().equals(null)||c.getTelefone().equals(null))
        return;

        repository.save(c);
    }

    public void remover(Cliente c){
        repository.delete(c);
    }

    public Cliente getClienteById(int id) {
		return repository.findById(id).get();
    }
}