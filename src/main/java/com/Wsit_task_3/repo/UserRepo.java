package com.Wsit_task_3.repo;

import com.Wsit_task_3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE name = ?1 AND password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    User findByEmail(String email);
}
