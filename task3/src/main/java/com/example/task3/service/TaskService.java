package com.example.task3.service;


import com.example.task3.model.Task;
import com.example.task3.model.Users;
import com.example.task3.repository.TaskRepository;
import com.example.task3.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.task3.dto.TaskResponseDTO;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // get logged in user
    private Users getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //create task
    public TaskResponseDTO createTask(String title, String description) {

        Users user = getCurrentUser();

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setCreatedAt(LocalDateTime.now());
        task.setOwner(user);

        Task saved = taskRepository.save(task);

        return new TaskResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getCreatedAt()
        );
    }

    // get tasks for logged in user
    public List<TaskResponseDTO> getMyTasks() {

        Users user = getCurrentUser();

        return taskRepository.findByOwner(user)
                .stream()
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreatedAt()
                ))
                .toList();
    }
}
