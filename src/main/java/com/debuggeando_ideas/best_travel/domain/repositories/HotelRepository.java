package com.debuggeando_ideas.best_travel.domain.repositories;

import com.debuggeando_ideas.best_travel.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

//    La siguiente consulta la haremos con lenguaje inclusivo de Spring Data
    Set<HotelEntity> findByPriceLessThan(BigDecimal price);
    Set<HotelEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
    Set<HotelEntity> findByName(String name);
    Set<HotelEntity> findByRatingGreaterThan(Integer rating);
//    La siguiente consulta join la haremos con lenguaje inclusivo de Spring Data
    Optional<HotelEntity> findByReservationId(UUID id);

}
