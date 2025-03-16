package com.ats.todolist.service;

import com.ats.todolist.dto.ToDoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ToDoService {

    ToDoDto createToDo(ToDoDto toDoDto);

    ToDoDto getToDoById(Long id);

    ToDoDto updateToDo(ToDoDto toDoDto, Long id);

    ToDoDto softDeleteToDo(Long id);

    ToDoDto completeToDo(Long id);

    Page<ToDoDto> searchToDos(String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
