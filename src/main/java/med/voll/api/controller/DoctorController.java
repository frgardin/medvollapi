package med.voll.api.controller;

import med.voll.api.domain.Doctor;
import med.voll.api.domain.DoctorRepository;
import med.voll.api.dtos.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public String getAllDoctors() {
        return "";
    }

    @PostMapping
    public void postDoctor(@RequestBody DoctorDto doctorDto) {
        repository.save(new Doctor(doctorDto));
    }
}
