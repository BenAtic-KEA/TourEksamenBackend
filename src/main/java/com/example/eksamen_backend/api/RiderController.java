package com.example.eksamen_backend.api;

import com.example.eksamen_backend.dto.RiderRequest;
import com.example.eksamen_backend.dto.RiderResponse;
import com.example.eksamen_backend.services.RiderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    public RiderResponse getRider(@PathVariable("id") int id) {
        return riderService.getRider(id);
    }

    @GetMapping("/team/{id}")
    public List<RiderResponse> getRidersByTeam(@PathVariable("id") int id) {
        return riderService.getRidersByTeam(id);
    }
    @PostMapping()
    public RiderResponse addRider(@RequestBody RiderRequest rider) {
        return riderService.addRider(rider);
    }


    @PutMapping("/{id}")
    public RiderResponse updateRider(@PathVariable("id") int id, @RequestBody RiderRequest rider) {
        return riderService.updateRider(id, rider);
    }

    @DeleteMapping("/{id}")
    public void deleteRider(@PathVariable("id") int id) {
        riderService.deleteRider(id);
    }

    @GetMapping("/yellow")
    public RiderResponse fastestRider(){
        return riderService.getRiderByFastestTime();
    }

    @GetMapping("/white")
    public RiderResponse fastestYouthRider(){
        return riderService.getRiderByYouthFastestTime();
    }

    @GetMapping("/mountain")
    public RiderResponse mostMountainPoints(){
        return riderService.getRiderMostMountainPoints();
    }

    @GetMapping("/sprint")
    public RiderResponse mostSprintPoints(){
        return riderService.getRiderMostSprintPoints();
    }
}
