package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Doctor;
import med.voll.api.domain.DoctorRepository;
import med.voll.api.dtos.DoctorDto;
import med.voll.api.dtos.DoctorListResponse;
import med.voll.api.dtos.DoctorUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public Page<DoctorListResponse> getDoctors(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(DoctorListResponse::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid
                             DoctorUpdateDto doctorUpdateDto) {
        Doctor doctor = repository.getReferenceById(doctorUpdateDto.id());
        doctor.updateInfo(doctorUpdateDto);
    }

    @PostMapping
    @Transactional
    public void createDoctor(@RequestBody @Valid DoctorDto doctorDto) {
        repository.save(new Doctor(doctorDto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.remove();
    }

}
