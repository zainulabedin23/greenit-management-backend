package net.javaguides.greenit_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plant_details")
public class PlantDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @Column(name = "plant_id", nullable = false)
    private Long plantId;

    @Column(name = "scientific_name", length = 255)
    private String scientificName;

    @Column(name = "physical_characteristics", columnDefinition = "TEXT")
    private String physicalCharacteristics;

    @Column(name = "medicinal_uses", columnDefinition = "TEXT")
    private String medicinalUses;

    @Column(name = "cautions", columnDefinition = "TEXT")
    private String cautions;

    @Transient
    private Plant plant; // Used for convenience in the application layer
}
