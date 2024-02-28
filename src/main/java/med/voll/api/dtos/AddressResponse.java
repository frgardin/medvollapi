package med.voll.api.dtos;

import med.voll.api.domain.Address;

public record AddressResponse(
        String street,
        String district,
        String cep,
        String number,
        String complement,
        String city,
        String uf
) {
    public AddressResponse(Address address) {
        this(
                address.getStreet(),
                address.getDistrict(),
                address.getCep(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getUf()
        );
    }
}
