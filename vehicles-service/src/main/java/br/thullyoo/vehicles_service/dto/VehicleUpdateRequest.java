package br.thullyoo.vehicles_service.dto;

import java.util.UUID;

public record VehicleUpdateRequest(
        String model,
        String mark,
        String color,
        UUID establishmentId
) {
}
