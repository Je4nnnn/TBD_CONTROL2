package com.example.TaskManager.Controller;

import com.example.TaskManager.DTO.TaskDTO;
import com.example.TaskManager.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // GET /tasks?userId=1  -> lista de tareas del usuario
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByUser(@RequestParam Long userId) {
        List<TaskDTO> tasks = taskService.getTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    }

    // POST /tasks  (en el body viene title, description, dueDate, important, userId, sectorId)
    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {
        TaskDTO created = taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO dto) {
        TaskDTO updated = taskService.updateTask(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
