package com.snookerbooking.controller;

import com.snookerbooking.dto.SnookerTableDTO;
import com.snookerbooking.service.SnookerTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snooker-tables")
public class SnookerTableController {

    private final SnookerTableService snookerTableService;

    public SnookerTableController(SnookerTableService snookerTableService) {
        this.snookerTableService = snookerTableService;
    }

    @PostMapping
    public SnookerTableDTO createSnookerTable(@RequestBody SnookerTableDTO dto) {
        if (dto.getOwnerId() == null) {
            throw new RuntimeException("Owner ID is required");
        }
        return snookerTableService.createSnookerTable(dto);
    }

    @GetMapping
    public List<SnookerTableDTO> getAllSnookerTables() {
        return snookerTableService.getAllSnookerTables();
    }

    @GetMapping("/{id}")
    public SnookerTableDTO getSnookerTableById(@PathVariable Long id) {
        return snookerTableService.getSnookerTableById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSnookerTable(@PathVariable Long id) {
        snookerTableService.deleteSnookerTable(id);
        return "Snooker Table deleted successfully";
    }

    @GetMapping("/owner/{ownerId}")
    public List<SnookerTableDTO> getSnookerTablesByOwner(@PathVariable Long ownerId) {
        return snookerTableService.getSnookerTablesByOwner(ownerId);
    }

    @GetMapping("/search")
    public List<SnookerTableDTO> searchTables(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return snookerTableService.searchTables(city, minPrice, maxPrice);
    }

}
