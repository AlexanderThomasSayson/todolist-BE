package com.ats.todolist.utils.mapper;

import com.ats.todolist.dto.ToDoDto;
import com.ats.todolist.entity.ToDo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ToDoMapper {

    public ToDo mapToEntity(ToDoDto toDoDto) {
        if (toDoDto == null) {
            return null;
        }
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoDto.getTitle());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setDateCreated(LocalDateTime.now());
        toDo.setDateModified(LocalDateTime.now());
        toDo.setCompleted(false);
        toDo.setStatus(0);
        return toDo;
    }

    public ToDoDto mapToDto(ToDo toDo) {
        if (toDo == null) {
            return null;
        }
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setId(toDo.getId());
        toDoDto.setTitle(toDo.getTitle());
        toDoDto.setDescription(toDo.getDescription());
        toDoDto.setDateCreated(LocalDateTime.now());
        toDoDto.setDateModified(LocalDateTime.now());
        toDoDto.setCompleted(toDo.isCompleted());
        toDoDto.setStatus(toDo.getStatus());
        return toDoDto;
    }
}