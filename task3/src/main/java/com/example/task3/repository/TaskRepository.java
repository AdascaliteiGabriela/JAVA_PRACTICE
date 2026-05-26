package com.example.task3.repository;


import com.example.task3.model.Task;
import com.example.task3.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwner(Users user);

}
