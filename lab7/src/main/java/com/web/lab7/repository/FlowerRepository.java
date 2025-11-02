package com.web.lab7.repository;

import com.web.lab7.model.flower.Flower;
import com.web.lab7.model.flower.FlowerType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository wrapper to access flower records stored in PostgreSQL.
 */
@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {

    /**
     * Locates the first flower of the requested type if present.
     *
     * @param type flower species to search for
     * @return optional matching flower instance
     */
    Optional<Flower> findFirstByType(FlowerType type);
}
