package com.ats.todolist.dao;

import com.ats.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrEmail(String userName, String email);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);
}
