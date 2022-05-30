package com.example.eksamen_backend.services;

import com.example.eksamen_backend.dto.TeamResponse;
import com.example.eksamen_backend.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<TeamResponse> getTeams() {
        return teamRepository.findAll().stream().map(team -> new TeamResponse(team.getId(), team.getName())).collect(Collectors.toList());
    }
}
