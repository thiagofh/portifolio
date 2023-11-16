package br.com.portifolio.controller;

import br.com.portifolio.model.Membro;
import br.com.portifolio.model.MembroId;
import br.com.portifolio.service.MembroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membros")
@Tag(name = "API de Membros", description = "Api Membros.")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @Operation(
            summary = "Listar todos os membros",
            description = "Listar todos os membros",
            tags = { "Membros", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Membro.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/listarMembros/")
    public ResponseEntity<List<Membro>> listarMembros() {
        List<Membro> membros = membroService.listarMembros();
        return ResponseEntity.ok(membros);
    }

    @Operation(
            summary = "Buscar membro por ID do Projeto",
            description = "Buscar membro por ID do Projeto",
            tags = { "Membros", "get" })
    @GetMapping("/buscarMembroPorProjeto/")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Membro.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Membro> buscarMembroPorProjeto(
            @RequestBody @Parameter(name = "Objeto Membro para ser atualizado.", required = true) Membro pMembro)  {
        Optional<Membro> membro = membroService.buscarMembroPorIdProjeto(pMembro);
        return membro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Adicionar Novo Membro",
            description = "Adicionar novo mebro.",
            tags = { "Membros", "post" })
    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Membro.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Membro> adicionarMembro(
            @RequestBody @Parameter(name = "Objeto Membro que deve ser adicionado.", required = true) Membro membro) {
        Membro novoMembro = membroService.salvarMembro(membro);
        return ResponseEntity.ok(novoMembro);
    }

    @Operation(
            summary = "Atualizar membro por ID do Projeto",
            description = "Adicionar novo mebro.",
            tags = { "Membros", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Membro.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping
    public ResponseEntity<Membro> atualizarMembro(
            @RequestBody @Parameter(name = "Objeto Membro que deve ser atualizado.", required = true) Membro pMembro) {
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

    @Operation(
            summary = "Deletar membro por ID do Projeto",
            description = "Deletar membro por ID do Projeto",
            tags = { "Membros", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Membro.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping
    public ResponseEntity<Void> deletarMembro(
            @RequestBody @Parameter(name = "Objeto Membro que deve ser excluido.", required = true) Membro pMembro) {
        membroService.deletarMembroPorIdProjeto(pMembro);
        return ResponseEntity.noContent().build();
    }
}
