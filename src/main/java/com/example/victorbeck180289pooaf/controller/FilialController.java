package com.example.victorbeck180289pooaf.controller;

import java.util.List;

import com.example.victorbeck180289pooaf.entity.Agendamento;
import com.example.victorbeck180289pooaf.entity.Barbeiro;
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
public class FilialController {
    @Autowired
    BarbeiroService bs;

    @Autowired
    ClienteService cs;

    @Autowired
    FilialService fs;

    @Autowired
    AgendamentoService as;

    @GetMapping("/filiais")
    public ModelAndView getFiliais(){
        ModelAndView mv = new ModelAndView("filiaisTemplate");
        mv.addObject("filial", new Filial());
        mv.addObject("filiais", fs.getFiliais());
    
        return mv;
    }

    @PostMapping("/salvarFilial")
    public String salvarFilial(@ModelAttribute Filial filial){
        fs.salvar(filial);
        return "redirect:/filiais";
    }

    @GetMapping("/removerFilial")
    public String removerFilial(@RequestParam Integer id){
        
        Filial filial = fs.getFilialById(id);
        List<Agendamento> agendamentos = as.getAgendamentosFilial(filial);
        if(agendamentos.size()==0)
        {
        fs.remover(filial);
        return "redirect:/filiais";
        }
        else
        return "redirect:/ER";
    }



    @GetMapping("/detalhesFilial/{id}")
    public ModelAndView getFilialDetalhes(@PathVariable(name = "id") Integer codigo) {
    
        Filial filial = fs.getFilialById(codigo);
        List<Barbeiro> barbeiros = filial.getBarbeiros();
        ModelAndView mv = new ModelAndView("detalhesFilial");
        mv.addObject("barbeiros", barbeiros);
        mv.addObject("filial", filial);
        return mv;
    }
}