package com.example.eksamen_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class RiderRequest {

    private String country;
    private String name;
    private int age;
    private double time;
    private int mountainPoints;
    private int sprintPoints;

    private int teamId;

}
