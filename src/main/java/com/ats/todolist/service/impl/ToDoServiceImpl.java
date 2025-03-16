package com.ats.todolist.service.impl;

import com.ats.todolist.dao.ToDoDao;
import com.ats.todolist.dto.ToDoDto;
import com.ats.todolist.entity.ToDo;
import com.ats.todolist.exception.ResourceNotFoundException;
import com.ats.todolist.service.ToDoService;
import com.ats.todolist.utils.constants.ErrorMessages;
import com.ats.todolist.utils.mapper.ToDoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoDao toDoDao;
    private final ToDoMapper toDoMapper;

    public ToDoServiceImpl(ToDoDao toDoDao, ToDoMapper toDoMapper) {
        this.toDoDao = toDoDao;
        this.toDoMapper = toDoMapper;
    }

    @Override
    public ToDoDto createToDo(ToDoDto toDoDto) {
        ToDo toDo = toDoMapper.mapToEntity(toDoDto);
        ToDo savedToDo = toDoDao.save(toDo);
        return toDoMapper.mapToDto(savedToDo);
    }

    @Override
    public ToDoDto getToDoById(Long id) {
        ToDo toDo = toDoDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format(ErrorMessages.TASK_NOT_FOUND,id))
        );
        return toDoMapper.mapToDto(toDo);
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {

        ToDo toDo = toDoDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format(ErrorMessages.TASK_NOT_FOUND,id))
        );

        toDo.setTitle(toDoDto.getTitle());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setCompleted(toDoDto.isCompleted());

        ToDo updatedToDo = toDoDao.save(toDo);
        return toDoMapper.mapToDto(updatedToDo);
    }

    @Override
    public ToDoDto softDeleteToDo(Long id) {
        ToDo toDo = toDoDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format(ErrorMessages.TASK_NOT_FOUND,id))
        );
        toDo.setStatus(1);

        ToDo softDeleteToDo = toDoDao.save(toDo);
        return toDoMapper.mapToDto(softDeleteToDo);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
        ToDo toDo = toDoDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.TASK_NOT_FOUND,id))
                );
        toDo.setCompleted(true);

        ToDo updatedToDo = toDoDao.save(toDo);
        return toDoMapper.mapToDto(updatedToDo);
    }
}
