package com.example.victorbeck180289pooaf.controller;

import java.util.List;

import com.example.victorbeck180289pooaf.entity.Agendamento;
import com.example.victorbeck180289pooaf.entity.Barbeiro;
import com.example.victorbeck180289pooaf.entity.Cliente;
import com.example.victorbeck180289pooaf.service.AgendamentoService;
import com.example.victorbeck180289pooaf.service.BarbeiroService;
import com.example.victorbeck180289pooaf.service.ClienteService;
import com.example.victorbeck180289pooaf.service.FilialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
    @Autowired
    BarbeiroService bs;

    @Autowired
    ClienteService cs;

    @Autowired
    FilialService fs;

    @Autowired
    AgendamentoService as;

    @PostMapping("/salvarCliente")
    public String salvaCliente(@ModelAttribute Cliente cliente){
        cs.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes")
    public ModelAndView getCliente(){
        ModelAndView mv = new ModelAndView("clientesTemplate");
        mv.addObject("cliente", new Cliente());
        mv.addObject("clientes", cs.getClientes());
    
        return mv;
    }

    @GetMapping("/removerCliente")
    public String removerCliente(@RequestParam Integer id){
        
        Cliente cliente = cs.getClienteById(id);
        List<Agendamento> agendamentos = as.getAgendamentosCliente(cliente);
        if(agendamentos.size()==0)
        {
        cs.remover(cliente);
        return "redirect:/clientes";
        }
        else
        return "redirect:/ER";
    }

    @GetMapping("/detalhesCliente/{id}")
public ModelAndView getClienteDetalhes(@PathVariable(name = "id") Integer codigo) {

    Cliente cliente = cs.getClienteById(codigo);
    ModelAndView mv = new ModelAndView("detalhesCliente");
    mv.addObject("cliente", cliente);
    return mv;
}
}