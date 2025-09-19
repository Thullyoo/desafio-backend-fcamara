package br.thullyoo.vehicles_service.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TB_VEHICLE")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    public UUID id;

    @JsonProperty(namespace = "model")
    @Column(name = "model")
    public String model;

    @JsonProperty(namespace = "mark")
    @Column(name = "mark")
    public String mark;

    @JsonProperty(namespace = "color")
    @Column(name = "color")
    public String color;

    @JsonProperty(namespace = "type")
    @Column(name = "type")
    public VehicleType type;

    @JsonProperty(namespace = "licensePlate")
    @Column(name = "license_plate", unique = true)
    public String licensePlate;

    @JsonProperty(namespace = "establishmentId")
    @Column(name = "establishment_id")
    private UUID establishmentId;
}
