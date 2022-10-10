package com.Wsit_task_3.controller;

import com.Wsit_task_3.repo.TeamRepo;
import com.Wsit_task_3.repo.UserRepo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;

    public TeamController(UserRepo userRepo, TeamRepo teamRepo) {
        this.userRepo = userRepo;
        this.teamRepo = teamRepo;
    }
}
