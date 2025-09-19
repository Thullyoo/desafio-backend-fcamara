package br.thullyoo.establishment_service.service;

import br.thullyoo.establishment_service.dto.EstablishmentRequest;
import br.thullyoo.establishment_service.model.establishment.Establishment;
import br.thullyoo.establishment_service.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public Establishment findById(UUID id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Establishment not found"));
    }

    public Establishment save(EstablishmentRequest establishment) {
        Establishment newEstablishment = new Establishment();
        newEstablishment.setName(establishment.name());
        newEstablishment.setAddress(establishment.address());
        newEstablishment.setPhoneNumber(establishment.phoneNumber());
        newEstablishment.setMotorcycleParkingSpaces(establishment.motorcycleParkingSpaces());
        newEstablishment.setCarParkingSpaces(establishment.carParkingSpaces());
        return establishmentRepository.save(newEstablishment);
    }

    public Establishment update(UUID id, EstablishmentRequest updated) {
        Establishment establishment = findById(id);
        establishment.setName(updated.name());
        establishment.setAddress(updated.address());
        establishment.setPhoneNumber(updated.phoneNumber());
        establishment.setMotorcycleParkingSpaces(updated.motorcycleParkingSpaces());
        establishment.setCarParkingSpaces(updated.carParkingSpaces());
        return establishmentRepository.save(establishment);
    }

    public void delete(UUID id) {
        Establishment establishment = findById(id);
        establishmentRepository.delete(establishment);
    }
}