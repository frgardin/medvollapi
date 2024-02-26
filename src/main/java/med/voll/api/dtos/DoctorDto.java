package med.voll.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Doctor;
import med.voll.api.enums.ExpertiseEnum;

public record DoctorDto(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotBlank
        String telephone,
        @JsonProperty("expertise")
        @NotNull
        ExpertiseEnum expertiseDto,
        @JsonProperty("address")
        @NotNull
        @Valid
        AddressDto addressDto
) {
}
