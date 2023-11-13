package br.com.portifolio.controller;

import br.com.portifolio.model.Projeto;
import br.com.portifolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public String listarProjetos(Model model) {
        List<Projeto> projetos = projetoService.listarProjetos();
        model.addAttribute("projetos", projetos);
        return "listarProjetos";
    }

    @GetMapping("/form")
    public String exibirFormulario(Model model) {
        model.addAttribute("projeto", new Projeto());
        return "formularioProjeto";
    }

    @PostMapping("/salvar")
    public String salvarProjeto(@ModelAttribute("projeto") Projeto projeto) {
        projetoService.salvarProjeto(projeto);
        return "redirect:/projetos";
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        Optional<Projeto> projeto = projetoService.buscarProjetoPorId(id);
        projeto.ifPresent(value -> model.addAttribute("projeto", value));
        return "formularioProjeto";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return "redirect:/projetos";
    }
}
