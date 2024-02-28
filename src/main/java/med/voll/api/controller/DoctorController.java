package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Doctor;
import med.voll.api.domain.DoctorRepository;
import med.voll.api.dtos.DoctorDto;
import med.voll.api.dtos.DoctorListResponse;
import med.voll.api.dtos.DoctorResponse;
import med.voll.api.dtos.DoctorUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public ResponseEntity<Page<DoctorListResponse>> getDoctors(Pageable pageable) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(pageable).map(DoctorListResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(new DoctorResponse(repository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid
                                       DoctorUpdateDto doctorUpdateDto) {
        Doctor doctor = repository.getReferenceById(doctorUpdateDto.id());
        doctor.updateInfo(doctorUpdateDto);
        return ResponseEntity.ok(new DoctorResponse(doctor));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody @Valid DoctorDto doctorDto, UriComponentsBuilder uriBuilder) {
        Doctor doctor = repository.save(new Doctor(doctorDto));
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorResponse(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.remove();
        return ResponseEntity.noContent().build();
    }

}
