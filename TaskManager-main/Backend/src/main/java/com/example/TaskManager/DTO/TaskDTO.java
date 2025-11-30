package com.example.TaskManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean finished;
    private boolean important;

    private Long userId;
    private Long sectorId;
    private String sectorName;
}
