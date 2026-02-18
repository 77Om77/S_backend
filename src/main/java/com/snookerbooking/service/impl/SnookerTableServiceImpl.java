package com.snookerbooking.service.impl;

import com.snookerbooking.dto.SnookerTableDTO;
import com.snookerbooking.entity.SnookerTable;
import com.snookerbooking.exception.ResourceNotFoundException;
import com.snookerbooking.repository.SnookerTableRepository;
import com.snookerbooking.service.SnookerTableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SnookerTableServiceImpl implements SnookerTableService {

    private final SnookerTableRepository snookerTableRepository;

    public SnookerTableServiceImpl(SnookerTableRepository snookerTableRepository) {
        this.snookerTableRepository = snookerTableRepository;
    }

    @Override
    public SnookerTableDTO createSnookerTable(SnookerTableDTO dto) {
        SnookerTable t = new SnookerTable();

        t.setName(dto.getName());
        t.setCity(dto.getCity());
        t.setLocation(dto.getLocation());
        t.setPricePerHour(dto.getPricePerHour());
        t.setAvailable(dto.isAvailable());
        t.setOwnerId(dto.getOwnerId());

        SnookerTable saved = snookerTableRepository.save(t);

        return toDTO(saved);
    }

    @Override
    public List<SnookerTableDTO> getAllSnookerTables() {
        return snookerTableRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SnookerTableDTO getSnookerTableById(Long id) {
        SnookerTable t = snookerTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snooker Table not found with id: " + id));
        return toDTO(t);
    }

    @Override
    public void deleteSnookerTable(Long id) {
        SnookerTable t = snookerTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snooker Table not found with id: " + id));
        snookerTableRepository.delete(t);
    }

    @Override
    public List<SnookerTableDTO> getSnookerTablesByOwner(Long ownerId) {
        return snookerTableRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SnookerTableDTO> searchTables(String city, Double minPrice, Double maxPrice) {
        return snookerTableRepository.searchTables(city, minPrice, maxPrice)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ======================
    // CENTRALIZED MAPPER
    // ======================
    private SnookerTableDTO toDTO(SnookerTable t) {
        SnookerTableDTO dto = new SnookerTableDTO();

        dto.setId(t.getId());
        dto.setName(t.getName());
        dto.setCity(t.getCity());
        dto.setLocation(t.getLocation());
        dto.setPricePerHour(t.getPricePerHour());
        dto.setAvailable(t.isAvailable());
        dto.setOwnerId(t.getOwnerId());

        return dto;
    }
}
