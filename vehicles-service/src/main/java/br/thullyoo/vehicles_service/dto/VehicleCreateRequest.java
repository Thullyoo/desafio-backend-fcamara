package br.thullyoo.vehicles_service.dto;

import java.util.UUID;

public record VehicleCreateRequest(
        String model,
        String mark,
        String color,
        String type,
        String licensePlate,
        UUID establishmentId
) {
}
