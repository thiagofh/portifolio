package br.com.portifolio.membro;

import br.com.portifolio.controller.MembroController;
import br.com.portifolio.model.Membro;
import br.com.portifolio.service.MembroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DisplayName("Teste para Membros.")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MembroControllerTest {


    @Mock
    private MembroService membroService;

    @InjectMocks
    private MembroController membroController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Listar Membros - Deve retornar lista de membros quando chamado")
    void listarMembros() {
        Membro membroCriado = criarMembroNaBase();

        List<Membro> membros = Arrays.asList(new Membro(), new Membro());
        when(membroService.listarMembros()).thenReturn(membros);

        ResponseEntity<List<Membro>> responseEntity = membroController.listarMembros();
        membroController.deletarMembro(membroCriado);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(membros, responseEntity.getBody());
    }

    @Test
    @DisplayName("Buscar Membro por Projeto - Deve retornar membro quando ID do projeto existente")
    void buscarMembroPorProjeto() {
        Membro membroCriado = criarMembroNaBase();
        Membro membro = new Membro();
        when(membroService.buscarMembroPorIdProjeto(membroCriado)).thenReturn(Optional.of(membro));

        ResponseEntity<Membro> responseEntity = membroController.buscarMembroPorProjeto(membroCriado);
        membroController.deletarMembro(membroCriado);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(membro, responseEntity.getBody());
    }

    @Test
    @DisplayName("Buscar Membro por Projeto - Deve retornar NOT FOUND quando ID do projeto não existente")
    void buscarMembroPorProjetoNotFound() {
        Membro membroCriado = criarMembroNaBase();

        when(membroService.buscarMembroPorIdProjeto(membroCriado)).thenReturn(Optional.empty());

        ResponseEntity<Membro> responseEntity = membroController.buscarMembroPorProjeto(membroCriado);
        membroController.deletarMembro(membroCriado);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Adicionar Novo Membro - Deve retornar membro adicionado quando chamado")
    void adicionarNovoMembro() {
        Membro membro = new Membro();
        membro.getId().setIdPessoa(1L);
        membro.getId().setIdPessoa(1L);
        when(membroService.salvarMembro(membro)).thenReturn(membro);

        ResponseEntity<Membro> responseEntity = membroController.adicionarMembro(membro);
        membroController.deletarMembro(membro);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(membro, responseEntity.getBody());
    }

    @Test
    @DisplayName("Deletar Membro - Deve retornar NO CONTENT quando ID do projeto existente")
    void deletarMembro() {
        Membro membroCriado = criarMembroNaBase();

        ResponseEntity<Void> responseEntity = membroController.deletarMembro(membroCriado);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(membroService, times(1)).deletarMembroPorIdProjeto(membroCriado);
    }

    private Membro criarMembroNaBase() {
        Membro membro = new Membro();
        membro.getId().setIdPessoa(1L);
        membro.getId().setIdPessoa(1L);

        Membro membroSalvo = membroService.salvarMembro(membro);

        return membroSalvo;
    }
}
