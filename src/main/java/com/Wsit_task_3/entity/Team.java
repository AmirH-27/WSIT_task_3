package com.Wsit_task_3.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @ManyToOne
    private User user;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    private List<User> users;

    public Team(String name, String password, User user) {
        this.name = name;
        this.password = password;
        this.user = user;
    }
}
