package net.javaguides.greenit_management.repository;

import net.javaguides.greenit_management.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    // JpaRepository provides CRUD operations and more for Plant entity
}