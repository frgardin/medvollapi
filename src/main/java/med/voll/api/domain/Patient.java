package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dtos.PatientDto;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String cpf;
    @Embedded
    private Address address;

    public Patient(PatientDto patientDto) {
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.telephone = patientDto.telephone();
        this.cpf = patientDto.cpf();
        this.address = new Address(patientDto.addressDto());
    }
}
