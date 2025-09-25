package br.thullyoo.vehicles_service.producer;

import br.thullyoo.vehicles_service.dto.RequestVehicleRegistryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VehicleProducer {

    private final String vehicleTopic = "vehicle-service-topic";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendVehicleRegistryRequest(RequestVehicleRegistryDTO dto) throws JsonProcessingException {
        String vehicleRequest = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send(vehicleTopic, vehicleRequest);
        System.out.println("Sent vehicle registration request for vehicle: " + dto.licensePlate() + " to establishment: " + dto.establishmentId());
    }
}
