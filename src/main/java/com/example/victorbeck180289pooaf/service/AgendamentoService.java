package com.example.victorbeck180289pooaf.service;

import java.util.ArrayList;
import java.util.List;

import com.example.victorbeck180289pooaf.entity.Agendamento;
import com.example.victorbeck180289pooaf.entity.Barbeiro;
import com.example.victorbeck180289pooaf.entity.Cliente;
import com.example.victorbeck180289pooaf.entity.Filial;
import com.example.victorbeck180289pooaf.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    AgendamentoRepository repository;
    
    public List<Agendamento> getAgendamentos(){
        return repository.findAll();
    }
    public List<Agendamento> getAgendamentosBarbeiro(Barbeiro b){
        List<Agendamento> agendamentos = repository.findAll();
        List<Agendamento> agendamentosassociados = new ArrayList<Agendamento>();
        for(Agendamento a: agendamentos)
        {
            if(a.getBarbeiro().equals(b))
            agendamentosassociados.add(a);
        }
        return agendamentosassociados;
    }

    public List<Agendamento> getAgendamentosCliente(Cliente b){
        List<Agendamento> agendamentos = repository.findAll();
        List<Agendamento> agendamentosassociados = new ArrayList<Agendamento>();
        for(Agendamento a: agendamentos)
        {
            if(a.getCliente().equals(b))
            agendamentosassociados.add(a);
        }
        return agendamentosassociados;
    }

    public List<Agendamento> getAgendamentosFilial(Filial b){
        List<Agendamento> agendamentos = repository.findAll();
        List<Agendamento> agendamentosassociados = new ArrayList<Agendamento>();
        for(Agendamento a: agendamentos)
        {
            if(a.getFilial().equals(b))
            agendamentosassociados.add(a);
        }
        return agendamentosassociados;
    }
    
    public void salvar(Agendamento a)
    {
        if(a.getBarbeiro().equals(null)||a.getCliente().equals(null)||a.getFilial().equals(null)||a.getData().equals(null))
        return;

        repository.save(a);
    }

    public void remover(Agendamento a){
        repository.delete(a);
    }

    public Agendamento getAgendamentoById(int id) {
		return repository.findById(id).get();
    }

}