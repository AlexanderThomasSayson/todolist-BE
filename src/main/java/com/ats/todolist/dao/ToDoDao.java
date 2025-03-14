package com.ats.todolist.dao;

import com.ats.todolist.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoDao extends JpaRepository<ToDo, Long> {
}
