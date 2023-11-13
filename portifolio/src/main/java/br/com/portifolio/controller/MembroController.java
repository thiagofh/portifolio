package br.com.portifolio.controller;

import br.com.portifolio.model.Membro;
import br.com.portifolio.model.MembroId;
import br.com.portifolio.service.MembroService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membros")
@Api(value = "API de Membros", tags = {"Membros"})
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping
    @ApiOperation(value = "Listar todos os membros", response = Membro.class, responseContainer = "List")
    public ResponseEntity<List<Membro>> listarMembros() {
        List<Membro> membros = membroService.listarMembros();
        return ResponseEntity.ok(membros);
    }

    @GetMapping
    @ApiOperation(value = "Buscar membro por ID do Projeto", response = Membro.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Membro encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Membro não encontrado")
    })
    public ResponseEntity<Membro> buscarMembroPorProjeto(
            @RequestBody @ApiParam(value = "Objeto Membro atualizado", required = true) Membro pMembro)  {
        Optional<Membro> membro = membroService.buscarMembroPorIdProjeto(pMembro);
        return membro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Adicionar novo membro", response = Membro.class)
    public ResponseEntity<Membro> adicionarMembro(
            @RequestBody @ApiParam(value = "Objeto Membro a ser adicionado", required = true) Membro membro) {
        Membro novoMembro = membroService.salvarMembro(membro);
        return ResponseEntity.ok(novoMembro);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar membro por ID do Projeto", response = Membro.class)
    public ResponseEntity<Membro> atualizarMembro(
            @RequestBody @ApiParam(value = "Objeto Membro atualizado", required = true) Membro pMembro) {
        Optional<Membro> membroExistente = membroService.buscarMembroPorIdProjeto(pMembro);

        if (membroExistente.isPresent()) {
            Membro membro = membroExistente.get();
            MembroId menMembroId = new MembroId();
            menMembroId.setIdProjeto(pMembro.getId().getIdProjeto());
            menMembroId.setIdPessoa(pMembro.getId().getIdPessoa());

            membro.setId(menMembroId);

            Membro membroAtualizado = membroService.salvarMembro(membro);
            return ResponseEntity.ok(membroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "Deletar membro por ID do Projeto")
    public ResponseEntity<Void> deletarMembro(
            @RequestBody @ApiParam(value = "Objeto Membro atualizado", required = true) Membro pMembro) {
        membroService.deletarMembroPorIdProjeto(pMembro);
        return ResponseEntity.noContent().build();
    }
}
