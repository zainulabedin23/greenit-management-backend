package net.javaguides.greenit_management.mapper;

import net.javaguides.greenit_management.dto.PlantDto;
import net.javaguides.greenit_management.entity.Category;
import net.javaguides.greenit_management.entity.Plant;
import net.javaguides.greenit_management.entity.User;
import net.javaguides.greenit_management.repository.CategoryRepository;
import net.javaguides.greenit_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlantMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // Convert Plant entity to PlantDto
    public PlantDto toDto(Plant plant) {
        PlantDto dto = new PlantDto();
        dto.setPlantId(plant.getPlantId());
        dto.setPlantName(plant.getPlantName());
        dto.setCategoryId(plant.getCategory() != null ? plant.getCategory().getCategoryId() : null);
        dto.setCreatedBy(plant.getCreatedBy() != null ? plant.getCreatedBy().getUserId() : null);
        return dto;
    }

    // Convert PlantDto to Plant entity
    public Plant toEntity(PlantDto plantDto) {
        Plant plant = new Plant();
        plant.setPlantId(plantDto.getPlantId());
        plant.setPlantName(plantDto.getPlantName());

        // Fetch Category and User based on IDs
        Category category = categoryRepository.findById(plantDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        User user = userRepository.findById(plantDto.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        plant.setCategory(category);
        plant.setCreatedBy(user);

        return plant;
    }
}
