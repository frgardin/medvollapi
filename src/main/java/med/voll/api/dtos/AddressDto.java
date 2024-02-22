package med.voll.api.dtos;

public record AddressDto(
        String street,
        String district,
        String cep,
        String city,
        String uf,
        String complement
) {
}
