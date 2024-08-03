package net.javaguides.greenit_management.service;

import net.javaguides.greenit_management.dto.PlantDto;
import net.javaguides.greenit_management.entity.Category;
import net.javaguides.greenit_management.entity.Plant;
import net.javaguides.greenit_management.entity.User;
import net.javaguides.greenit_management.mapper.PlantMapper;
import net.javaguides.greenit_management.repository.CategoryRepository;
import net.javaguides.greenit_management.repository.UserRepository;
import net.javaguides.greenit_management.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    // Method to update a plant
    public Plant updatePlant(Long plantId, String plantName, int categoryId, Long updatedById) {
        Plant existingPlant = plantRepository.findById(plantId)
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User updatedBy = userRepository.findById(updatedById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingPlant.setPlantName(plantName);
        existingPlant.setCategory(category);
        existingPlant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        // Optionally, you can also set updatedBy if you have a field for that

        return plantRepository.save(existingPlant);
    }
    public Plant addPlant(String plantName, int categoryId, Long createdById) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User createdBy = userRepository.findById(createdById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Plant plant = new Plant();
        plant.setPlantName(plantName);
        plant.setCategory(category);
        plant.setCreatedBy(createdBy);
        plant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        plant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return plantRepository.save(plant);
    }
}