package br.com.portifolio.service;

import br.com.portifolio.model.Pessoa;
import br.com.portifolio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {return pessoaRepository.save(pessoa);}

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

}