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

    public RiderResponse addRider(RiderRequest rider) {
        Rider newRider = new Rider(rider.getName(),rider.getAge(),rider.getTime(),rider.getMountainPoints(),rider.getSprintPoints(),rider.getCountry());
        Team team = teamRepository.findById(rider.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
        newRider.setTeam(team);
        Rider riderResponse = riderRepository.save(newRider);
        return new RiderResponse(riderResponse);
    }

    public RiderResponse updateRider(int id, RiderRequest rider) {
        Rider r = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));

        r.setAge(rider.getAge());
        r.setCountry(rider.getCountry());
        r.setMountainPoints(rider.getMountainPoints());
        r.setName(rider.getName());
        r.setSprintPoints(rider.getSprintPoints());
        r.setTime(rider.getTime());

        Rider savedRider = riderRepository.save(r);
        return new RiderResponse(savedRider);
    }

    public void deleteRider(int id) {
        Rider r = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));
        riderRepository.delete(r);
    }

    public RiderResponse getRider(int id) {
        return new RiderResponse(riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found")));
    }

    public List<RiderResponse> getRidersByTeam(int id) {
        return riderRepository.findRidersByTeamId(id).stream().map(RiderResponse::new).collect(Collectors.toList());
    }

    public RiderResponse getRiderByFastestTime(){
        List<Rider> riders = riderRepository.findAll();
        Rider fastestRider = new Rider();
        for(Rider rider : riders){
            if(rider.getTime() < fastestRider.getTime() | fastestRider.getTime() == 0){
                fastestRider = rider;
            }
        }
        return new RiderResponse(fastestRider);
    }

    public RiderResponse getRiderByYouthFastestTime(){
        List<Rider> riders = riderRepository.findAll();
        Rider fastestYouthRider = new Rider();
        for(Rider rider : riders){

            if(rider.getTime() < fastestYouthRider.getTime() & rider.getAge() < 26 | fastestYouthRider.getTime() == 0 & rider.getAge() < 26){
                fastestYouthRider = rider;
            }
        }
        return new RiderResponse(fastestYouthRider);
    }

    public RiderResponse getRiderMostMountainPoints() {
        List<Rider> riders = riderRepository.findAll();
        Rider riderMostPoints = new Rider();
        for(Rider rider : riders){
            if(rider.getMountainPoints() > riderMostPoints.getMountainPoints() | riderMostPoints.getMountainPoints() == 0){
                riderMostPoints = rider;
            }
        }
        return new RiderResponse(riderMostPoints);
    }

    public RiderResponse getRiderMostSprintPoints() {
        List<Rider> riders = riderRepository.findAll();
        Rider riderMostPoints = new Rider();
        for(Rider rider : riders){
            if(rider.getSprintPoints() > riderMostPoints.getSprintPoints() | riderMostPoints.getSprintPoints() == 0){
                riderMostPoints = rider;
            }
        }
        return new RiderResponse(riderMostPoints);

    }


}
