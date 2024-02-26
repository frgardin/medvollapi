package med.voll.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.ExpertiseEnum;

public record DoctorUpdateDto(
        @NotNull
        Long id,
        String name,
        String telephone,
        @JsonProperty("address")
        @Valid
        AddressDto addressDto
) {
}
