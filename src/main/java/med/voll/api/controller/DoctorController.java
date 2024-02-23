package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Doctor;
import med.voll.api.domain.DoctorRepository;
import med.voll.api.dtos.DoctorDto;
import med.voll.api.dtos.DoctorListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public Page<DoctorListResponse> getDoctors(Pageable pageable) {
        return repository.findAll(pageable).map(DoctorListResponse::new);
    }

    @PostMapping
    @Transactional
    public void postDoctor(@RequestBody @Valid DoctorDto doctorDto) {
        repository.save(new Doctor(doctorDto));
    }
}
