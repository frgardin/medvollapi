package med.voll.api.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dtos.AddressDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String cep;
    private String number;
    private String complement;
    private String city;
    private String uf;

    public Address(AddressDto addressDto) {
        this.street = addressDto.street();
        this.district = addressDto.district();
        this.cep = addressDto.cep();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
        this.city = addressDto.city();
        this.uf = addressDto.uf();
    }

    public void updateInfo(AddressDto addressDto) {
        if (addressDto.street())
            this.street
    }
}
