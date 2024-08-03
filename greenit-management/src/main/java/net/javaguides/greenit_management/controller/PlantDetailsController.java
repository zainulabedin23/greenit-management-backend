package net.javaguides.greenit_management.controller;

import net.javaguides.greenit_management.entity.PlantDetails;
import net.javaguides.greenit_management.service.PlantDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plant-details")
public class PlantDetailsController {

    @Autowired
    private PlantDetailsService plantDetailsService;

    @GetMapping
    public List<PlantDetails> getAllPlantDetails() {
        return plantDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantDetails> getPlantDetailsById(@PathVariable Long id) {
        Optional<PlantDetails> plantDetails = plantDetailsService.findById(id);
        return plantDetails.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlantDetails> createPlantDetails(@RequestBody PlantDetails plantDetails) {
        try {
            PlantDetails createdPlantDetails = plantDetailsService.save(plantDetails);
            return ResponseEntity.status(201).body(createdPlantDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantDetails> updatePlantDetails(@PathVariable Long id, @RequestBody PlantDetails updatedDetails) {
        return plantDetailsService.findById(id)
                .map(existingDetails -> {
                    updatedDetails.setDetailId(existingDetails.getDetailId());
                    return ResponseEntity.ok(plantDetailsService.save(updatedDetails));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlantDetails(@PathVariable Long id) {
        if (plantDetailsService.findById(id).isPresent()) {
            plantDetailsService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
