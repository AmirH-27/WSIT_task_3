package com.Wsit_task_3.repo;

import com.Wsit_task_3.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepo extends JpaRepository<TempUser, Integer> {

    TempUser findByEmail(String email);
}
