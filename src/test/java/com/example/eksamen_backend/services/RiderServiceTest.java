package com.example.eksamen_backend.services;

import com.example.eksamen_backend.dto.RiderRequest;
import com.example.eksamen_backend.entities.Rider;
import com.example.eksamen_backend.entities.Team;
import com.example.eksamen_backend.repositories.RiderRepository;
import com.example.eksamen_backend.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RiderServiceTest {

    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private TeamRepository teamRepository;

    private RiderService riderService;

    static Rider rider1,rider2,rider3,rider4;
    static int team1Id,team2Id;

    @BeforeEach
    void setUp(@Autowired TeamRepository teamRepository, @Autowired RiderRepository riderRepository) {
        riderService = new RiderService(riderRepository, teamRepository);
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

        team1Id = teamRepository.save(team1).getId();
        team2Id = teamRepository.save(team2).getId();


    }

    @Test
    void addRider() {
        //arrange
        RiderRequest rider5 = new RiderRequest("Denmark","Test123",30,120,1,12,team1Id);
        int riderReposizeBefore,riderReposizeAfter;
        //act
        riderReposizeBefore = riderRepository.findAll().size();
        riderService.addRider(rider5);
        riderReposizeAfter = riderRepository.findAll().size();
        //assert
        assertTrue(riderReposizeBefore < riderReposizeAfter);


    }

    @Test
    void updateRider() {
        //arrange
        String orgName,newName;
        int repoSizeBefore,repoSizeAfter;
        RiderRequest updatedRider = new RiderRequest("Denmark","newName",30,120,1,12,team1Id);
        //act
        repoSizeBefore = riderRepository.findAll().size();
        orgName = rider1.getName();
        riderService.updateRider(rider1.getId(),updatedRider);
        repoSizeAfter = riderRepository.findAll().size();
        newName = riderRepository.findById(rider1.getId()).get().getName();

        //assert

        assertNotEquals(orgName,newName);
        assertEquals("newName",newName);
        assertEquals(repoSizeBefore, repoSizeAfter);
    }

    @Test
    void deleteRider() {
        //arrange
        int riderReposizeBefore,riderReposizeAfter;
        int teamRepoSizeBefore,teamRepoSizeAfter;
        //act
        riderReposizeBefore = riderRepository.findAll().size();
        teamRepoSizeBefore = teamRepository.findAll().size();
        riderService.deleteRider(rider1.getId());
        riderReposizeAfter = riderRepository.findAll().size();
        teamRepoSizeAfter = teamRepository.findAll().size();
        //assert
        assertTrue(riderReposizeBefore > riderReposizeAfter);
        assertEquals(teamRepoSizeBefore, teamRepoSizeAfter);
    }
}