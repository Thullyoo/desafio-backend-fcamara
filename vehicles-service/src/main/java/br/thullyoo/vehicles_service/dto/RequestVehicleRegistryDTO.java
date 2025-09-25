package br.thullyoo.vehicles_service.dto;

import java.util.UUID;

public record RequestVehicleRegistryDTO(String licensePlate, String type, UUID establishmentId) {
}
