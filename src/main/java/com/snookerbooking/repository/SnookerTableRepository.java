package com.snookerbooking.repository;

import com.snookerbooking.entity.SnookerTable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnookerTableRepository extends JpaRepository<SnookerTable, Long> {
    List<SnookerTable> findByOwnerId(Long ownerId);

    @org.springframework.data.jpa.repository.Query("SELECT t FROM SnookerTable t WHERE " +
            "(:city IS NULL OR LOWER(t.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
            "(:minPrice IS NULL OR t.pricePerHour >= :minPrice) AND " +
            "(:maxPrice IS NULL OR t.pricePerHour <= :maxPrice)")
    List<SnookerTable> searchTables(@org.springframework.data.repository.query.Param("city") String city,
            @org.springframework.data.repository.query.Param("minPrice") Double minPrice,
            @org.springframework.data.repository.query.Param("maxPrice") Double maxPrice);

}
