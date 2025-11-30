package com.example.TaskManager.Service;

import com.example.TaskManager.DTO.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getTasksForUser(Long userId);

    TaskDTO createTask(TaskDTO dto);

    TaskDTO updateTask(Long id, TaskDTO dto);

    void deleteTask(Long id);
}
