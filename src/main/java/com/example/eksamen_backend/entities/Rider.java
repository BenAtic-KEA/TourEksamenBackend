package com.example.eksamen_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rider {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;
    private String name;
    private int age;
    private double time;
    private int mountainPoints;
    private int sprintPoints;

    public Rider(String name, int age, double time, int mountainPoints, int sprintPoints, String country) {
        this.name = name;
        this.age = age;
        this.time = time;
        this.mountainPoints = mountainPoints;
        this.sprintPoints = sprintPoints;
        this.country = country;
    }

    @ManyToOne()
    private Team team;
}
