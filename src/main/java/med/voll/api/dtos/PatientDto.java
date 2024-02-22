package med.voll.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PatientDto(
        String name,
        String email,
        String telephone,
        String cpf,
        @JsonProperty("address")
        AddressDto addressDto
) {
}
