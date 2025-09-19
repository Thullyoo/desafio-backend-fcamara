package br.thullyoo.vehicles_service.repository;

import br.thullyoo.vehicles_service.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    List<Vehicle> findByEstablishmentId(UUID establishmentId);
}
