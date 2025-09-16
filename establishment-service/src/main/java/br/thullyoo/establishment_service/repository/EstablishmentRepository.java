package br.thullyoo.establishment_service.repository;

import br.thullyoo.establishment_service.model.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, UUID> {
}
