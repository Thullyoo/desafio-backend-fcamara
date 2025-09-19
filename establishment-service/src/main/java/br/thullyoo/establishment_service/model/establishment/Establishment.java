package br.thullyoo.establishment_service.model.establishment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_ESTABLISHMENT")
@RequiredArgsConstructor
@Data
public class Establishment {

    @Id
    @JsonProperty(namespace = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonProperty(namespace = "name")
    @Column(name = "name")
    private String name;

    @JsonProperty(namespace = "address")
    @Column(name = "address")
    private String address;

    @JsonProperty(namespace = "phoneNumber")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @JsonProperty(namespace = "motorcycleParkingSpaces")
    @Column(name = "motorcycle_parking_spaces")
    private Long motorcycleParkingSpaces;

    @JsonProperty(namespace = "carParkingSpaces")
    @Column(name = "car_parking_spaces")
    private Long carParkingSpaces;
}
