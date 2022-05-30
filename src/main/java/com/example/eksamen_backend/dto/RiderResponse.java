package com.example.eksamen_backend.dto;

import com.example.eksamen_backend.entities.Rider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RiderResponse {

    private int id;
    private String country;
    private String name;
    private int age;
    private double time;
    private int mountainPoints;
    private int sprintPoints;

    private TeamResponse team;

    public RiderResponse(Rider body){
        this.id = body.getId();
        this.country = body.getCountry();
        this.name = body.getName();
        this.age = body.getAge();
        this.time = body.getTime();
        this.mountainPoints = body.getMountainPoints();
        this.sprintPoints = body.getSprintPoints();
        if(body.getTeam() != null){
        this.team = new TeamResponse(body.getTeam());
        }
    }
}
