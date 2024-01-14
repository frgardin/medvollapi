package med.voll.api.controller;

import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados,
                                    UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);
        URI uri = uriComponentsBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(
                        new DadosDetalhamentoPaciente(paciente)
                );
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listagemPacientes (@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
            return ResponseEntity.ok(repository
                    .findAllByAtivoTrue(pageable)
                    .map(DadosListagemPaciente::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(repository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }
    
}