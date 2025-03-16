package com.ats.todolist.service.impl;

import com.ats.todolist.dao.ToDoDao;
import com.ats.todolist.dto.ToDoDto;
import com.ats.todolist.entity.ToDo;
import com.ats.todolist.service.ToDoService;
import com.ats.todolist.utils.ToDoUtils;
import com.ats.todolist.utils.mapper.ToDoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
public class ToDoServiceImpl implements ToDoService {

    private final ToDoDao toDoDao;
    private final ToDoMapper toDoMapper;
    private final ToDoUtils toDoUtils;

    public ToDoServiceImpl(ToDoDao toDoDao, ToDoMapper toDoMapper, ToDoUtils toDoUtils) {
        this.toDoDao = toDoDao;
        this.toDoMapper = toDoMapper;
        this.toDoUtils = toDoUtils;
    }

    @Override
    public ToDoDto createToDo(ToDoDto toDoDto) {
        log.info("Creating a new ToDo: {}", toDoDto);
        ToDo toDo = toDoMapper.mapToEntity(toDoDto);
        ToDo savedToDo = toDoDao.save(toDo);
        log.info("ToDo created successfully with ID: {}", savedToDo.getId());
        return toDoMapper.mapToDto(savedToDo);
    }

    @Override
    public ToDoDto getToDoById(Long id) {
        log.info("Fetching ToDo with ID: {}", id);
        ToDo toDo = toDoUtils.findToDoById(id);
        return toDoMapper.mapToDto(toDo);
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
        log.info("Updating ToDo with ID: {}", id);
        ToDo toDo = toDoUtils.findToDoById(id);

        toDo.setTitle(toDoDto.getTitle());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setCompleted(toDoDto.isCompleted());

        ToDo updatedToDo = toDoDao.save(toDo);
        log.info("ToDo with ID: {} updated successfully", id);
        return toDoMapper.mapToDto(updatedToDo);
    }

    @Override
    public ToDoDto softDeleteToDo(Long id) {
        log.info("Soft deleting ToDo with ID: {}", id);
        ToDo toDo = toDoUtils.findToDoById(id);

        toDo.setStatus(1);
        ToDo softDeletedToDo = toDoDao.save(toDo);
        log.info("ToDo with ID: {} soft deleted successfully", id);
        return toDoMapper.mapToDto(softDeletedToDo);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
        log.info("Marking ToDo as completed with ID: {}", id);
        ToDo toDo = toDoUtils.findToDoById(id);

        toDo.setCompleted(true);
        ToDo updatedToDo = toDoDao.save(toDo);
        log.info("ToDo with ID: {} marked as completed", id);
        return toDoMapper.mapToDto(updatedToDo);
    }

    @Override
    public Page<ToDoDto> searchToDos(String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        log.info("Fetching ToDos with keyword: {}, startDate: {}, endDate: {}", keyword, startDate, endDate);

        Page<ToDo> toDos;
        if (keyword == null || keyword.isBlank()) {
            toDos = toDoDao.findByDateRange(startDate, endDate, pageable);
        } else {
            toDos = toDoDao.findByKeywordAndDateRange(keyword, startDate, endDate, pageable);
        }

        return toDos.map(toDoMapper::mapToDto);
    }
}
