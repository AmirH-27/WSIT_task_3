package com.Wsit_task_3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TempUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String token;
    @ManyToOne
    private Team team;

    private boolean isJoined;

    public TempUser(String email, String token, Team team, boolean isJoined) {
        this.email = email;
        this.token = token;
        this.team = team;
        this.isJoined = isJoined;
    }
}
