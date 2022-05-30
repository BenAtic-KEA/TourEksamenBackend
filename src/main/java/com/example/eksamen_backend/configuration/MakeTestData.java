package com.example.eksamen_backend.configuration;

import com.example.eksamen_backend.entities.Rider;
import com.example.eksamen_backend.entities.Team;
import com.example.eksamen_backend.repositories.RiderRepository;
import com.example.eksamen_backend.repositories.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    RiderRepository riderRepository;
    TeamRepository teamRepository;

    public MakeTestData(RiderRepository riderRepository, TeamRepository teamRepository) {
        this.riderRepository = riderRepository;
        this.teamRepository = teamRepository;
    }

    public void makeTeamsWithRiders(){

        Team team1 = new Team("Premier Tech");
        Rider rider1 = new Rider("Mads Würtz",28,120,0,30,"Denmark");
        Rider rider2 = new Rider("Chris Froome",37,110,0,70,"England");
        Rider rider3 = new Rider("Jakob Fuglsang",37,115.5,2,45,"Denmark");
        Rider rider4 = new Rider("Michael Woods",35,140,0,0,"Canada");

        team1.addRiders(Set.of(rider1,rider2,rider3,rider4));


        Team team2 = new Team("Lotto");
        Rider rider5 = new Rider("Brent Van Moer",24,160,0,10,"Belgium");
        Rider rider6 = new Rider("Tim Wellens",31,150,0,0,"Belgium");
        Rider rider7 = new Rider("Phillipe Gilbert",39,170.55,0,2,"Belgium");
        Rider rider8 = new Rider("Caleb Ewan",27,165,0,12,"Australia");

        team2.addRiders(Set.of(rider5,rider6,rider7,rider8));

        Team team3 = new Team("Quick-Step");
        Rider rider9 = new Rider("Michael Mørkøv",37,160,0,30,"Denmark");
        Rider rider10 = new Rider("Julian Alaphilippe",29,146,0,90,"France");
        Rider rider11 = new Rider("Kasper Asgreen",27,163.2,1,0,"Denmark");
        Rider rider12 = new Rider("Yves Lampaert",31,180,0,0,"Belgium");

        team3.addRiders(Set.of(rider9,rider10,rider11,rider12));

        Team team4 = new Team("Jumbo-Visma");
        Rider rider13 = new Rider("Jonas Vingegaard",25,170,0,10,"Denmark");
        Rider rider14 = new Rider("Primož Roglic ",32,150.6,0,40,"Slovenia");

        team4.addRiders(Set.of(rider13,rider14));

        teamRepository.saveAll(Set.of(team1,team2,team3,team4));

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        makeTeamsWithRiders();
    }

}
