package br.com.portifolio.service;

import br.com.portifolio.exceptions.ProjetoNotFoundException;
import br.com.portifolio.exceptions.StatusProjetoInvalidoException;
import br.com.portifolio.model.Projeto;
import br.com.portifolio.model.enums.StatusProjetoEnum;
import br.com.portifolio.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void deletarProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() -> new ProjetoNotFoundException(id));

        if (projeto.getStatus() == StatusProjetoEnum.INICIADO ||
                projeto.getStatus() == StatusProjetoEnum.EM_ANDAMENTO ||
                projeto.getStatus() == StatusProjetoEnum.ENCERRADO) {
            throw new StatusProjetoInvalidoException(projeto.getStatus());
        }

        projetoRepository.delete(projeto);
    }

}
