package br.thullyoo.vehicles_service.listener;

import br.thullyoo.vehicles_service.dto.RegistryRequestResponseDTO;
import br.thullyoo.vehicles_service.model.vehicle.Vehicle;
import br.thullyoo.vehicles_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VehicleListener {

    @Autowired
    private VehicleRepository repository;

    @KafkaListener(
        topics = "establishment-service-topic",
        groupId = "vehicle-service-group"
    )
    public void listenRegistryRequest(RegistryRequestResponseDTO dto){
        if (!dto.approved()){
            Optional<Vehicle> vehicle = repository.findByLicensePlate(dto.licensePlate());
            if (vehicle.isEmpty()){
                System.out.println("Vehicle with license plate " + dto.licensePlate() + " not found.");
                System.out.println("Vehicle registration not approved for vehicle: " + dto.licensePlate());
                return;
            }
            vehicle.get().setEstablishmentId(null);
            repository.save(vehicle.get());
            System.out.println("Vehicle registration not approved for vehicle: " + dto.licensePlate());
            return;
        }
        System.out.println("Vehicle registration approved for vehicle: " + dto.licensePlate() + " by establishment: " + dto.establishmentId());
    }
}
