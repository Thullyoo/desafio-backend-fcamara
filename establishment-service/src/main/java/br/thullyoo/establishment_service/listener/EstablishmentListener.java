package br.thullyoo.establishment_service.listener;

import br.thullyoo.establishment_service.dto.RegistryRequestResponseDTO;
import br.thullyoo.establishment_service.dto.RequestVehicleRegistryDTO;
import br.thullyoo.establishment_service.model.establishment.Establishment;
import br.thullyoo.establishment_service.producer.EstablishmentProducer;
import br.thullyoo.establishment_service.repository.EstablishmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class EstablishmentListener {

    @Autowired
    private EstablishmentRepository repository;

    @Autowired
    private EstablishmentProducer producer;

    @KafkaListener(
        topics = "vehicle-service-topic"
        , groupId = "establishment-service-group"
    )
    public void listenRequestRegistry(RequestVehicleRegistryDTO dto){
        Optional<Establishment> establishment = repository.findById(dto.establishmentId());

        try {

            if(establishment.isEmpty()){
                System.out.println("Estabelecimento não encontrado: " + dto.establishmentId());
                // to do avisar que o estabelecimento não foi encontrado
                return;
            }

            if (Objects.equals(dto.type(), "CAR")){
                if (establishment.get().getCarParkingSpaces() > 0){
                    establishment.get().setCarParkingSpaces(establishment.get().getCarParkingSpaces() - 1);
                    repository.save(establishment.get());
                    producer.sendVehicleRegistryResponse(new RegistryRequestResponseDTO(dto.licensePlate(),dto.establishmentId(), true));
                } else{
                    producer.sendVehicleRegistryResponse(new RegistryRequestResponseDTO(dto.licensePlate(),dto.establishmentId(), false));
                }
                return;
            } else if (Objects.equals(dto.type(), "MOTORCYCLE")) {
                if (establishment.get().getMotorcycleParkingSpaces() > 0){
                    establishment.get().setCarParkingSpaces(establishment.get().getMotorcycleParkingSpaces() - 1);
                    repository.save(establishment.get());
                    producer.sendVehicleRegistryResponse(new RegistryRequestResponseDTO(dto.licensePlate(),dto.establishmentId(), true));
                } else{
                    producer.sendVehicleRegistryResponse(new RegistryRequestResponseDTO(dto.licensePlate(),dto.establishmentId(), false));
                }
                return;
            }

        } catch (RuntimeException | JsonProcessingException e){
            System.out.println("Erro ao tentar registrar veículo no estabelecimento");
        }


    }
}
