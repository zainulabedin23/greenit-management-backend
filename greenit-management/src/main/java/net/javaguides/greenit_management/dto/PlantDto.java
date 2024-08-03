package net.javaguides.greenit_management.dto;

import lombok.Data;

@Data
public class PlantDto {
    private Long plantId;     // Updated to Long
    private String plantName;
    private int categoryId;  // Updated to Long
    private Long createdBy;   // Updated to Long
}
