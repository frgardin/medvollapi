package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Patient;
import med.voll.api.domain.PatientRepository;
import med.voll.api.dtos.PatientDto;
import med.voll.api.dtos.PatientListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping
    public Page<PatientListResponse> getPatientsPaginated (Pageable pageable) {
        return repository.findAll(pageable).map(PatientListResponse::new);
    }

    @PostMapping
    @Transactional
    public void postPatient(@RequestBody @Valid PatientDto patientDto) {
        repository.save(new Patient(patientDto));
    }

}
