package br.thullyoo.establishment_service.producer;

import br.thullyoo.establishment_service.dto.RegistryRequestResponseDTO;
import br.thullyoo.establishment_service.dto.RequestVehicleRegistryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentProducer {

    private final String vehicleTopic = "establishment-service-topic";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendVehicleRegistryResponse(RegistryRequestResponseDTO dto) throws JsonProcessingException {
        String vehicleResponse = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send(vehicleTopic, vehicleResponse);
        System.out.println("Sent vehicle registration response for vehicle: " + dto.licensePlate() + " to establishment: " + dto.establishmentId());
    }

}
