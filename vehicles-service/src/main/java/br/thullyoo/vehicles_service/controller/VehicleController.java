package br.thullyoo.vehicles_service.controller;

import br.thullyoo.vehicles_service.dto.VehicleCreateRequest;
import br.thullyoo.vehicles_service.dto.VehicleUpdateRequest;
import br.thullyoo.vehicles_service.model.vehicle.Vehicle;
import br.thullyoo.vehicles_service.services.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping()
    public Vehicle create(@RequestBody VehicleCreateRequest vehicleCreateRequest) {
        return vehicleService.create(vehicleCreateRequest);
    }

    @PutMapping
    public Vehicle update(@RequestParam("id") UUID id, @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        return vehicleService.update(id, vehicleUpdateRequest);
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable("id") UUID id) {
        return vehicleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        vehicleService.delete(id);
    }

    @PostMapping("/assign-driver")
    public void assignDriver(@RequestParam("licensePlate") String licensePlate, @RequestParam("establishmentId") UUID establishmentId, @RequestParam("type") String type) throws JsonProcessingException {
        vehicleService.registerVehicleAtEstablishment(licensePlate, type,  establishmentId);
    }

}
