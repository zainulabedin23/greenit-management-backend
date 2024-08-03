package net.javaguides.greenit_management.repository;

import net.javaguides.greenit_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be added here
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}