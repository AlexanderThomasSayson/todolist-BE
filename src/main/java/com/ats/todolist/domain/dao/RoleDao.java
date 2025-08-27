package com.ats.todolist.domain.dao;

import com.ats.todolist.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);
}
