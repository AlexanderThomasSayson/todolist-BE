package com.ats.todolist.dao;

import com.ats.todolist.entity.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ToDoDao extends JpaRepository<ToDo, Long> {

    @Query("SELECT t FROM ToDo t WHERE " +
            "(:keyword IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:startDate IS NULL OR t.dateCreated >= :startDate) " +
            "AND (:endDate IS NULL OR t.dateCreated <= :endDate)")
    Page<ToDo> findByKeywordAndDateRange(@Param("keyword") String keyword,
                                         @Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate,
                                         Pageable pageable);

    @Query("SELECT t FROM ToDo t WHERE " +
            "(:startDate IS NULL OR t.dateCreated >= :startDate) " +
            "AND (:endDate IS NULL OR t.dateCreated <= :endDate)")
    Page<ToDo> findByDateRange(@Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate,
                               Pageable pageable);

}
