package com.example.eksamen_backend.repositories;

import com.example.eksamen_backend.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
    List<Rider> findRidersByTeamId(int teamId);
}
