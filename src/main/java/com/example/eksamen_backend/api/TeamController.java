package com.example.eksamen_backend.api;

import com.example.eksamen_backend.services.TeamService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

}
