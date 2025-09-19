package br.thullyoo.vehicles_service.services;

import br.thullyoo.vehicles_service.dto.VehicleCreateRequest;
import br.thullyoo.vehicles_service.dto.VehicleUpdateRequest;
import br.thullyoo.vehicles_service.model.vehicle.Vehicle;
import br.thullyoo.vehicles_service.model.vehicle.VehicleType;
import br.thullyoo.vehicles_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(UUID id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public Vehicle create(VehicleCreateRequest vehicle) {
        Vehicle vehicleToSave = new Vehicle();
        vehicleToSave.setModel(vehicle.model());
        vehicleToSave.setMark(vehicle.mark());
        vehicleToSave.setColor(vehicle.color());
        vehicleToSave.setType(vehicle.type() == VehicleType.MOTORCYCLE.name() ? VehicleType.MOTORCYCLE : VehicleType.CAR);
        vehicleToSave.setLicensePlate(vehicle.licensePlate());
        vehicleToSave.setEstablishmentId(vehicle.establishmentId());

        Vehicle saved = vehicleRepository.save(vehicleToSave);

        return saved;
    }

    public Vehicle update(UUID id, VehicleUpdateRequest updated) {
        Vehicle vehicle = findById(id);

        vehicle.setModel(updated.model());
        vehicle.setMark(updated.mark());
        vehicle.setColor(updated.color());
        vehicle.setEstablishmentId(updated.establishmentId());

        return vehicleRepository.save(vehicle);
    }

    public void delete(UUID id) {
        Vehicle vehicle = findById(id);
        vehicleRepository.delete(vehicle);
    }

    public List<Vehicle> findByEstablishment(UUID establishmentId) {
        return vehicleRepository.findByEstablishmentId(establishmentId);
    }

}
