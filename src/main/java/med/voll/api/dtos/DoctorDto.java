package med.voll.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import med.voll.api.enums.ExpertiseEnum;


public record DoctorDto(
        String name,
        String email,

        String crm,
        @JsonProperty("expertise")
        ExpertiseEnum expertiseDto,
        @JsonProperty("address")
        AddressDto addressDto
) {
}
