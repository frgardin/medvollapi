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
        @Email
        String email,
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        String telephone,
        @JsonProperty("expertise")
        ExpertiseEnum expertiseEnum,
        @JsonProperty("address")
        @Valid
        AddressDto addressDto
) {
}
