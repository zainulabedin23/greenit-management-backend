package net.javaguides.greenit_management.service;

import net.javaguides.greenit_management.entity.Plant;
import net.javaguides.greenit_management.entity.PlantDetails;
import net.javaguides.greenit_management.repository.PlantDetailsRepository;
import net.javaguides.greenit_management.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantDetailsService {

    @Autowired
    private PlantDetailsRepository plantDetailsRepository;

    @Autowired
    private PlantRepository plantRepository;

    public List<PlantDetails> findAll() {
        return plantDetailsRepository.findAll();
    }

    public Optional<PlantDetails> findById(Long id) {
        return plantDetailsRepository.findById(id);
    }

    public PlantDetails save(PlantDetails plantDetails) {
        // Fetch the Plant entity using plantId
        Plant plant = plantRepository.findById(plantDetails.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + plantDetails.getPlantId()));

        // Set the Plant entity
        plantDetails.setPlant(plant);

        return plantDetailsRepository.save(plantDetails);
    }

    public void deleteById(Long id) {
        plantDetailsRepository.deleteById(id);
    }
}
