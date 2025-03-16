package com.ats.todolist.service;

import com.ats.todolist.dto.ToDoDto;

public interface ToDoService {

    ToDoDto createToDo(ToDoDto toDoDto);

    ToDoDto getToDoById(Long id);

    ToDoDto updateToDo(ToDoDto toDoDto, Long id);

    ToDoDto softDeleteToDo(Long id);

    ToDoDto completeToDo(Long id);
}
