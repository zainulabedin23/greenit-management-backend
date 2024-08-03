package net.javaguides.greenit_management.controller;

import net.javaguides.greenit_management.dto.PlantDto;
import net.javaguides.greenit_management.entity.Plant;
import net.javaguides.greenit_management.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestParam String plantName,
                                             @RequestParam int categoryId,
                                             @RequestParam Long createdById) {
        Plant newPlant = plantService.addPlant(plantName, categoryId, createdById);
        return ResponseEntity.ok(newPlant);
    }

    // Endpoint to get all plants
    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }

    // Endpoint to update a plant
    @PutMapping("/{plantId}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long plantId,
                                             @RequestParam String plantName,
                                             @RequestParam int categoryId,
                                             @RequestParam Long updatedById) {
        Plant updatedPlant = plantService.updatePlant(plantId, plantName, categoryId, updatedById);
        return ResponseEntity.ok(updatedPlant);
    }
}
