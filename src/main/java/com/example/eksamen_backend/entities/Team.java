package com.example.eksamen_backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Rider> riders = new HashSet<>();

    public Team(String name) {
        this.name = name;
    }

    public void addRider(Rider rider){
        this.riders.add(rider);
        rider.setTeam(this);
    }

    public void addRiders(Set<Rider> riders){
        this.riders = riders;
        for(Rider rider : riders){
            rider.setTeam(this);
        }
    }

    public void removeRider(Rider rider){
        this.riders.remove(rider);
        rider.setTeam(null);
    }

    public void removeAllRiders(){
        for(Rider rider : this.riders){
            rider.setTeam(null);
        }
        this.riders.clear();
    }
}
