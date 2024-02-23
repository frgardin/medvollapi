package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.Patient;
import med.voll.api.domain.PatientRepository;
import med.voll.api.dtos.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    public void postPatient(@RequestBody @Valid PatientDto patientDto) {
        repository.save(new Patient(patientDto));
    }

}
