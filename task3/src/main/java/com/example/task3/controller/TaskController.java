package com.example.task3.controller;


import com.example.task3.dto.TaskRequestDTO;
import com.example.task3.dto.TaskResponseDTO;
import com.example.task3.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // create task
    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO request) {
        return taskService.createTask(
                request.title(),
                request.description()
        );
    }

    // get logged in user task
    @GetMapping("/my")
    public List<TaskResponseDTO> getMyTasks() {
        return taskService.getMyTasks();
    }


    // get all tasks(for admin)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // delete task (for admin)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
