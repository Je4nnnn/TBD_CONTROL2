package com.example.TaskManager.Service;

import com.example.TaskManager.DTO.TaskDTO;
import com.example.TaskManager.Entity.Sector;
import com.example.TaskManager.Entity.Task;
import com.example.TaskManager.Entity.User;
import com.example.TaskManager.Mapper.TaskMapper;
import com.example.TaskManager.Repository.SectorRepository;
import com.example.TaskManager.Repository.TaskRepository;
import com.example.TaskManager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SectorRepository sectorRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDTO> getTasksForUser(Long userId) {
        // Se ajustó el nombre del método en el repositorio a findByUser_Id
        List<Task> tasks = taskRepository.findByUser_Id(userId);
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Sector sector = null;
        if (dto.getSectorId() != null) {
            sector = sectorRepository.findById(dto.getSectorId())
                    .orElseThrow(() -> new RuntimeException("Sector no encontrado"));
        }

        Task task = taskMapper.toEntity(dto, user, sector);
        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Sector sector = null;
        if (dto.getSectorId() != null) {
            sector = sectorRepository.findById(dto.getSectorId())
                    .orElseThrow(() -> new RuntimeException("Sector no encontrado"));
        }

        taskMapper.updateEntity(task, dto, user, sector);
        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}