package com.example.eksamen_backend.services;

import com.example.eksamen_backend.dto.RiderRequest;
import com.example.eksamen_backend.dto.RiderResponse;
import com.example.eksamen_backend.entities.Rider;
import com.example.eksamen_backend.entities.Team;
import com.example.eksamen_backend.repositories.RiderRepository;
import com.example.eksamen_backend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiderService {

    RiderRepository riderRepository;
    TeamRepository teamRepository;

    public RiderService(RiderRepository riderRepository, TeamRepository teamRepository) {
        this.riderRepository = riderRepository;
        this.teamRepository = teamRepository;
    }

    public List<RiderResponse> getRiders() {
        return riderRepository.findAll().stream().map(RiderResponse::new).collect(Collectors.toList());

    }

    public void addRider(RiderRequest rider) {
        Team team = teamRepository.findById(rider.getTeamId()).get();
        Rider newRider = new Rider(rider.getName(),rider.getAge(),rider.getTime(),rider.getMountainPoints(),rider.getSprintPoints(),rider.getCountry());
        team.addRider(newRider);
        teamRepository.save(team);
    }

    public void updateRider(int id, RiderRequest rider) {
        Rider r = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));

        r.setAge(rider.getAge());
        r.setCountry(rider.getCountry());
        r.setMountainPoints(rider.getMountainPoints());
        r.setName(rider.getName());
        r.setSprintPoints(rider.getSprintPoints());
        r.setTime(rider.getTime());

        riderRepository.save(r);
    }

    public void deleteRider(int id) {
        Rider r = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));
        riderRepository.delete(r);
    }
}
