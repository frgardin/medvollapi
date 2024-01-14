package med.voll.api.controller;

import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody
            @Valid DadosCadastroMedico dados,
            UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = new Medico(dados);

        URI uri = uriComponentsBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medico.getId())
                .toUri();

        repository.save(medico);

        return ResponseEntity
                .created(uri)
                .body(
                        new DadosDetalhamentoMedico(medico)
                );
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable paginacao)
    {
        Page<DadosListagemMedico> page = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(
            @PathVariable Long id)
    {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(
            @RequestBody @Valid DadosAtualizacaoMedico dados)
    {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id)
    {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

}