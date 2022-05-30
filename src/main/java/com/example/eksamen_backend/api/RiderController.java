package com.example.eksamen_backend.api;

import com.example.eksamen_backend.dto.RiderRequest;
import com.example.eksamen_backend.dto.RiderResponse;
import com.example.eksamen_backend.services.RiderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/riders")
public class RiderController {

    RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping()
    public List<RiderResponse> getRiders() {
        return riderService.getRiders();
    }


    @PostMapping()
    public void addRider(@RequestBody RiderRequest rider) {
        riderService.addRider(rider);
    }

    @PutMapping("/{id}")
    public void updateRider(@PathVariable("id") int id, @RequestBody RiderRequest rider) {
        riderService.updateRider(id, rider);
    }

    @DeleteMapping("/{id}")
    public void deleteRider(@PathVariable("id") int id) {
        riderService.deleteRider(id);
    }
}
