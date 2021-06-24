package com.example.victorbeck180289pooaf.controller;

import java.util.List;

import com.example.victorbeck180289pooaf.entity.Agendamento;
import com.example.victorbeck180289pooaf.entity.Barbeiro;
import com.example.victorbeck180289pooaf.entity.Cliente;
import com.example.victorbeck180289pooaf.entity.Filial;
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
public class AgendamentoController {
    @Autowired
    BarbeiroService bs;

    @Autowired
    ClienteService cs;

    @Autowired
    FilialService fs;

    @Autowired
    AgendamentoService as;

    @GetMapping("/agendamentos")
    public ModelAndView getAgendamentos(){
        ModelAndView mv = new ModelAndView("agendamentosTemplate");
        mv.addObject("agendamento", new Agendamento());
        mv.addObject("agendamentos", as.getAgendamentos());
        List<Barbeiro> barbeiros = bs.getBarbeiros();
        List<Cliente> clientes = cs.getClientes();
        List<Filial> filiais = fs.getFiliais();
        mv.addObject("barbeiros", barbeiros);
        mv.addObject("filiais", filiais);
        mv.addObject("clientes", clientes);
        return mv;
    }

    @PostMapping("/salvarAgendamento")
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento){
        as.salvar(agendamento);
        return "redirect:/agendamentos";
    }

    @GetMapping("/removerAgendamento")
    public String removerAgendamento(@RequestParam Integer id){
        
        Agendamento agendamento = as.getAgendamentoById(id);
        as.remover(agendamento);
        return "redirect:/agendamentos";
    }

    @GetMapping("/detalhesAgendamento/{id}")
public ModelAndView getAgendamentoDetalhes(@PathVariable(name = "id") Integer codigo) {

    Agendamento agendamento = as.getAgendamentoById(codigo);
    ModelAndView mv = new ModelAndView("detalhesAgendamento");
    mv.addObject("agendamento", agendamento);
    List<Barbeiro> barbeiros = bs.getBarbeiros();
    List<Cliente> clientes = cs.getClientes();
    List<Filial> filiais = fs.getFiliais();
    mv.addObject("barbeiros", barbeiros);
    mv.addObject("filiais", filiais);
    mv.addObject("clientes", clientes);
    return mv;
}
}