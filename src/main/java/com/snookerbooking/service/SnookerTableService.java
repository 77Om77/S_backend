package com.snookerbooking.service;

import com.snookerbooking.dto.SnookerTableDTO;
import java.util.List;

public interface SnookerTableService {

    SnookerTableDTO createSnookerTable(SnookerTableDTO dto);

    List<SnookerTableDTO> getAllSnookerTables();

    SnookerTableDTO getSnookerTableById(Long id);

    void deleteSnookerTable(Long id);

    List<SnookerTableDTO> getSnookerTablesByOwner(Long ownerId);

    List<SnookerTableDTO> searchTables(String city, Double minPrice, Double maxPrice);

}
