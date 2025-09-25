package br.thullyoo.establishment_service.dto;

import java.util.UUID;

public record RegistryRequestResponseDTO(String licensePlate, UUID establishmentId, boolean approved) {
}
