package med.voll.api.dtos;

import med.voll.api.domain.Patient;

public record PatientListResponse(
        String name,
        String email,
        String telephone,
        String cpf
) {

    public PatientListResponse(Patient patient) {
        this(
                patient.getName(),
                patient.getEmail(),
                patient.getTelephone(),
                patient.getCpf()
        );
    }
}
