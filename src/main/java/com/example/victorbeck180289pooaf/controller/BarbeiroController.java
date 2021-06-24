package com.example.victorbeck180289pooaf.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class BarbeiroController {
    @Autowired
    BarbeiroService bs;

    @Autowired
    ClienteService cs;

    @Autowired
    FilialService fs;

    @Autowired
    AgendamentoService as;





    @GetMapping("/menu")
    public ModelAndView menu(){
    ModelAndView mv = new ModelAndView("menu");
    return mv;
    }

    @GetMapping("/ER")
    public ModelAndView ErroRemoção(){
    ModelAndView mv = new ModelAndView("ER");
    return mv;
    }

    @PostMapping("/salvarBarbeiro")
    public String salvarBarbeiro(@ModelAttribute Barbeiro barbeiro){
        bs.salvar(barbeiro);
        return "redirect:/barbeiros";
    }

    @GetMapping("/barbeiros")
    public ModelAndView getBarbeiros(){
        ModelAndView mv = new ModelAndView("barbeirosTemplate");
        mv.addObject("barbeiro", new Barbeiro());
        mv.addObject("barbeiros", bs.getBarbeiros());
    
        return mv;
    }

    @GetMapping("/removerBarbeiro")
public String removerBarbeiro(@RequestParam Integer id){
    
    Barbeiro barbeiro = bs.getBarbeiroById(id);
    List<Agendamento> agendamentos = as.getAgendamentosBarbeiro(barbeiro);
    if(agendamentos.size()==0)
    {
    bs.remover(barbeiro);
    return "redirect:/barbeiros";
    }
    else
    return "redirect:/ER";
}

@GetMapping("/detalhesBarbeiro/{id}")
public ModelAndView getBarbeiroDetalhes(@PathVariable(name = "id") Integer codigo) {

    Barbeiro barbeiro = bs.getBarbeiroById(codigo);
    ModelAndView mv = new ModelAndView("detalhesBarbeiro");
    mv.addObject("barbeiro", barbeiro);
    List <Filial> filiaisNaoAssociadas = fs.getFiliais();
    filiaisNaoAssociadas.removeAll(barbeiro.getFiliais());
    List<Agendamento> agendamentos=as.getAgendamentosBarbeiro(barbeiro);
    mv.addObject("agendamentos", agendamentos);
    mv.addObject("filiais", filiaisNaoAssociadas);
    return mv;
}

@PostMapping("/associarFilialBarbeiro")
public String associarFilial(@ModelAttribute Filial filial, @RequestParam Integer codigoBarbeiro) {
    

    Barbeiro barbeiro = bs.getBarbeiroById(codigoBarbeiro);
    filial = fs.getFilialById(filial.getId());
    

    barbeiro.getFiliais().add(filial);
    bs.salvar(barbeiro);

    return "redirect:/detalhesBarbeiro/" + codigoBarbeiro;
}

@GetMapping("/editarBarbeiro")
public ModelAndView editarBarbeiro(@RequestParam Integer id){
    
    ModelAndView mv = new ModelAndView("editarBarbeiro");

    Barbeiro barbeiro = bs.getBarbeiroById(id); 
    mv.addObject("barbeiro", barbeiro);
    mv.addObject("filiais", fs.getFiliais());

    return mv;

}

}