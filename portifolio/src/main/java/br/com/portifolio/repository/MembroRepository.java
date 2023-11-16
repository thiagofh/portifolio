package br.com.portifolio.repository;

import br.com.portifolio.model.Membro;
import br.com.portifolio.model.MembroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membro, MembroId> {

}

