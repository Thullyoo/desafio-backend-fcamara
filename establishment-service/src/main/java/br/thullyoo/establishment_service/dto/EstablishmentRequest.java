package br.thullyoo.establishment_service.dto;

public record EstablishmentRequest (
        String name,
        String address,
        String phoneNumber,
        Long motorcycleParkingSpaces,
        Long carParkingSpaces
){
}
