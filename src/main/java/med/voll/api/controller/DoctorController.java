package med.voll.api.controller;

import med.voll.api.dtos.DoctorDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {


    @GetMapping
    public String getAllDoctors() {
        return "";
    }

    @PostMapping
    public DoctorDto postDoctor(@RequestBody DoctorDto json) {
        return json;
    }
}
