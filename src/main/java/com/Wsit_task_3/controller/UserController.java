package com.Wsit_task_3.controller;

import com.Wsit_task_3.entity.Team;
import com.Wsit_task_3.entity.TempUser;
import com.Wsit_task_3.entity.User;
import com.Wsit_task_3.repo.TeamRepo;
import com.Wsit_task_3.repo.TempUserRepo;
import com.Wsit_task_3.repo.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;

    private final TempUserRepo tempUserRepo;
    public UserController(UserRepo userRepo, TeamRepo teamRepo, TempUserRepo tempUserRepo) {
        this.userRepo = userRepo;
        this.teamRepo = teamRepo;
        this.tempUserRepo = tempUserRepo;
    }

    @PostMapping("/login")
    public User login(@RequestParam("name") String username, @RequestParam("pass") String password) {
        User user = userRepo.findByUsernameAndPassword(username, password);
        if(user != null) {
            return user;
        }
        return null;
    }

    @PostMapping("/register")
    public User register(@RequestParam("name") String username, @RequestParam("email") String email, @RequestParam("pass") String password) {
        User user = new User(username, email, password);
        if(userRepo.findByUsernameAndPassword(username, password) == null) {
            userRepo.save(user);
            return user;
        }
        return null;
    }

    @PostMapping("/create/team")
    public Team createTeam(@RequestParam("id")int user_id, @RequestParam("team_pass")String password, @RequestParam("teamName") String teamName) {
        User user = userRepo.findById(user_id).get();
        if(user != null) {
            Team team = new Team(teamName, password, user);
            teamRepo.save(team);
            return team;
        }
        return null;
    }

    @PostMapping("/add/user")
    public TempUser addUser(@RequestParam("team_id") int teamId, @RequestParam("email") String email) {
        Team team = teamRepo.findById(teamId).get();
        TempUser tempUser = tempUserRepo.findByEmail(email);
        String token = generateToken();
        if(tempUser == null){
            tempUser = new TempUser(email, token, team, false);
            tempUserRepo.save(tempUser);
        }
        return tempUser;
    }
    @PostMapping("/join/team")
    public Team joinTeam(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("pass") String pass,
                         @RequestParam("team_id") int team_id, @RequestParam("token") String token) {
        Team team = teamRepo.findById(team_id).get();
        TempUser tempUser = tempUserRepo.findByEmail(email);
        if(tempUser != null && tempUser.getToken().equals(token)) {
            User user = new User(name, email, pass);
            userRepo.save(user);
            team.getUsers().add(user);
            teamRepo.save(team);
            tempUser.setJoined(true);
            tempUserRepo.save(tempUser);
            return team;
        }
        return null;
    }

    @GetMapping("/get/team")
    public Team getTeam(@RequestParam("id") int user_id){
        User user = userRepo.findById(user_id).get();
        if(user != null) {
            return teamRepo.findByUser_Id(user_id);
        }
        return null;
    }

    private String generateToken() {
        String token = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            token += random.nextInt(5);
        }
        return token;
    }
}
