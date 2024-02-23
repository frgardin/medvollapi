package med.voll.api.dtos;

import med.voll.api.domain.Doctor;
import med.voll.api.enums.ExpertiseEnum;

public record DoctorListResponse(
        String name,
        String email,
        String crm,
        ExpertiseEnum expertise
) {
    public DoctorListResponse(Doctor doctor) {
        this(
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getExpertise()
        );
    }

}
