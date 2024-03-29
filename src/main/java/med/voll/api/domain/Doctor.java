package med.voll.api.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dtos.DoctorDto;
import med.voll.api.dtos.DoctorUpdateDto;
import med.voll.api.enums.ExpertiseEnum;

@Table(name = "doctors")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private ExpertiseEnum expertise;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor(DoctorDto doctorDto) {
        this.active = true;
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.crm = doctorDto.crm();
        this.expertise = doctorDto.expertiseDto();
        this.address = new Address(doctorDto.addressDto());
    }

    public void updateInfo(DoctorUpdateDto doctorUpdateDto) {
        if (doctorUpdateDto.name() != null)
            this.name = doctorUpdateDto.name();
        if (doctorUpdateDto.telephone() != null)
            this.telephone = doctorUpdateDto.telephone();
        if (doctorUpdateDto.addressDto() != null)
            this.address.updateInfo(doctorUpdateDto.addressDto());

    }

    public void remove() {
        this.active = false;
    }
}
