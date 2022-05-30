package com.example.eksamen_backend.repositories;

import com.example.eksamen_backend.entities.Rider;
import com.example.eksamen_backend.entities.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class RiderRepositoryTest {
    @Autowired
    RiderRepository riderRepository;

    @Autowired
    TeamRepository teamRepository;


    static Rider rider1,rider2,rider3,rider4;
    @BeforeEach
    void setUp(@Autowired RiderRepository riderRepository, @Autowired TeamRepository teamRepository) {
        riderRepository.deleteAll();
        teamRepository.deleteAll();

        rider1 = new Rider("test1",25,120,1,1,"Denmark");
        rider2 = new Rider("test2",22,130,0,2,"England");
        rider3 = new Rider("test3",34,130,1,2,"Germany");
        rider4 = new Rider("test4",24,125,3,75,"England");

        Team team1 = new Team("team1");
        Team team2 = new Team("team2");

        team1.addRider(rider1);
        team1.addRider(rider2);
        team1.addRider(rider3);

        team2.addRider(rider4);

        teamRepository.save(team1);
        teamRepository.save(team2);
    }

    @Test
    void findRidersByTeamId() {
        //Arrange

        int team1Size, team2Size;
        Rider riderFromRepo;
        //Act

        team1Size = riderRepository.findRidersByTeamId(1).size();
        team2Size = riderRepository.findRidersByTeamId(2).size();
        riderFromRepo = riderRepository.findRidersByTeamId(2).get(0);


        //Assert

        assertEquals(3, team1Size);
        assertEquals(1, team2Size);

        assertEquals(rider4.getName(), riderFromRepo.getName());
    }

}
