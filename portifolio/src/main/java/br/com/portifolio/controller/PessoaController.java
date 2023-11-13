package br.com.portifolio.controller;

import br.com.portifolio.model.Pessoa;
import br.com.portifolio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);
        return "listarPessoas";
    }

    @GetMapping("/form")
    public String exibirFormulario(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "formularioPessoa";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
        pessoaService.salvarPessoa(pessoa);
        return "redirect:/pessoas";
    }

    @GetMapping("/editar/{id}")
    public String editarPessoa(@PathVariable Long id, Model model) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(id);
        pessoa.ifPresent(value -> model.addAttribute("pessoa", value));
        return "formularioPessoa";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return "redirect:/pessoas";
    }
}