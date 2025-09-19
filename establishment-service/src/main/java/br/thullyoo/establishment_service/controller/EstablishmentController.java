package br.thullyoo.establishment_service.controller;

import br.thullyoo.establishment_service.dto.EstablishmentRequest;
import br.thullyoo.establishment_service.model.establishment.Establishment;
import br.thullyoo.establishment_service.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/establishments")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @PostMapping
    public Establishment createEstablishment(@RequestBody EstablishmentRequest establishment) {
        return establishmentService.save(establishment);
    }

    @PutMapping("/{id}")
    public Establishment updateEstablishment(@PathVariable("id") UUID id,@RequestBody EstablishmentRequest establishment) {
        return establishmentService.update(id, establishment);
    }

    @GetMapping("/{id}")
    public Establishment getEstablishment(@PathVariable("id") UUID id) {
        return establishmentService.findById(establishmentService.findById(id).getId());
    }

    @DeleteMapping("/{id}")
    public void deleteEstablishment(@PathVariable("id") UUID id) {
        establishmentService.delete(id);
    }


}
