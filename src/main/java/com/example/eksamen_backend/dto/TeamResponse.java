package com.example.eksamen_backend.dto;

import com.example.eksamen_backend.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class TeamResponse {
    private int id;
    private String name;

    public TeamResponse(Team body) {
        this.id = body.getId();
        this.name = body.getName();
    }
}
