package med.voll.api.dtos;

import med.voll.api.domain.Doctor;
import med.voll.api.enums.ExpertiseEnum;

public record DoctorResponse(
        Long id,
        String name,
        String email,
        String crm,
        String telephone,
        ExpertiseEnum expertise,
        AddressResponse address
) {
    public DoctorResponse(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getTelephone(),
                doctor.getExpertise(),
                new AddressResponse(doctor.getAddress())
        );

    }
}
