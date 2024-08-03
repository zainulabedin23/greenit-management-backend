package net.javaguides.greenit_management.repository;

import net.javaguides.greenit_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}