package br.thullyoo.establishment_service.dto;

import java.util.UUID;

public record EstablishmentResponse(
        UUID id,
        String name,
        String address,
        String phoneNumber,
        Long motorcycleParkingSpaces,
        Long carParkingSpaces
) {}
