package com.ats.todolist.utils;

import com.ats.todolist.dao.ToDoDao;
import com.ats.todolist.entity.ToDo;
import com.ats.todolist.exception.ResourceNotFoundException;
import com.ats.todolist.utils.constants.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ToDoUtils {

    private final ToDoDao toDoDao;

    public ToDoUtils(ToDoDao toDoDao) {
        this.toDoDao = toDoDao;
    }

    /**
     * Finds a ToDo by ID, throws an exception if not found.
     */
    public ToDo findToDoById(Long id) {
        return toDoDao.findById(id)
                .orElseThrow(() -> {
                    log.error("ToDo with ID: {} not found", id);
                    return new ResourceNotFoundException(String.format(ErrorMessages.TASK_NOT_FOUND, id));
                });
    }
}
