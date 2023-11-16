package br.com.portifolio.service;

import br.com.portifolio.exceptions.DadosInvalidosException;
import br.com.portifolio.model.Membro;
import br.com.portifolio.model.MembroId;
import br.com.portifolio.model.Pessoa;
import br.com.portifolio.model.Projeto;
import br.com.portifolio.repository.MembroRepository;
import br.com.portifolio.repository.PessoaRepository;
import br.com.portifolio.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private final MembroRepository membroRepository;

    @Autowired
    private final PessoaRepository pessoaRepository;

    @Autowired
    private final ProjetoRepository projetoRepository;


    @Autowired
    public MembroService(MembroRepository membroRepository, PessoaRepository pessoaRepository, ProjetoRepository projetoRepository) {
        this.membroRepository = membroRepository;
        this.pessoaRepository = pessoaRepository;
        this.projetoRepository = projetoRepository;
    }

    public List<Membro> listarMembros() {
        return membroRepository.findAll();
    }

    public Optional<Membro> buscarMembroPorIdProjeto(Membro membro) {
        return membroRepository.findById(membro.getId());
    }

    public Membro salvarMembro(Membro pMmembro) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pMmembro.getId().getIdPessoa());
        Optional<Projeto> projetoOptional = projetoRepository.findById(pMmembro.getId().getIdProjeto());

        Membro membroSalvo = new Membro();

        if ((pessoaOptional.isPresent() && pessoaOptional.get().getFuncionario()) && (projetoOptional.isPresent()) ) {

            MembroId menMembroId = new MembroId();
            menMembroId.setIdProjeto(projetoOptional.get().getId());
            menMembroId.setIdPessoa(pessoaOptional.get().getId());

            Membro membro = new Membro();
            membro.setId(menMembroId);

            membroSalvo = membroRepository.save(membro);
        } else {
            throw new DadosInvalidosException("Não foi possível fazer a associação, por favor, valide os dados de entrada.");
        }

        return membroSalvo;

    }

    public void deletarMembroPorIdProjeto(Membro memo) {
        membroRepository.deleteById(memo.getId());
    }
}
