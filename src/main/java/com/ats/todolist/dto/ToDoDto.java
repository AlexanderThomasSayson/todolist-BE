package com.ats.todolist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto {

    private Long id;
    @NotNull(message = "title cannot be null")
    private String title;
    @NotNull(message = "description cannot be null")
    private String description;
    private boolean isCompleted;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private int status;

}
