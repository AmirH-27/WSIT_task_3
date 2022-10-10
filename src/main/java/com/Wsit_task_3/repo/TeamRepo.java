package com.Wsit_task_3.repo;

import com.Wsit_task_3.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepo extends JpaRepository<Team, Integer> {
    Team findByName(String teamName);


    @Query(value = "SELECT * FROM team WHERE user_id = ?1", nativeQuery = true)
    Team findByUser_Id(int user_id);
}
