package br.com.portifolio.repository;

import br.com.portifolio.model.Membro;
import br.com.portifolio.model.MembroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends JpaRepository<Membro, MembroId> {

    Optional<Membro> findByIdProjetoAndIdPessoa(Long idProjeto, Long idPessoa);
    void deleteByIdProjetoAndIdPessoa(Long idProjeto, Long idPessoa);
}

