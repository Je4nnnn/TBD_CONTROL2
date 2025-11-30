package com.example.TaskManager.Controller;

import com.example.TaskManager.DTO.TaskDTO;
import com.example.TaskManager.DTO.UserDTO;
import com.example.TaskManager.Entity.User;
import com.example.TaskManager.Entity.Task;
import com.example.TaskManager.Mapper.TaskMapper;
import com.example.TaskManager.Repository.TaskRepository;
import com.example.TaskManager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @GetMapping("/users")
    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(user ->
                UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .role(user.getRole().name())
                        .address(user.getAddress())
                        .latitude(user.getLatitude())
                        .longitude(user.getLongitude())
                        .build()
        ).toList();
    }

    @GetMapping("/tasks")
    public List<TaskDTO> listTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }
}