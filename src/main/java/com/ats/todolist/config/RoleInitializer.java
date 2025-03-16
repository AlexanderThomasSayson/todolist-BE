package com.ats.todolist.config;

import com.ats.todolist.dao.RoleDao;
import com.ats.todolist.entity.Role;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class RoleInitializer {

    private final RoleDao roleDao;

    public RoleInitializer(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PostConstruct
    @Transactional
    public void initializeRoles() {
        createRoleIfNotExists("USER");
        createRoleIfNotExists("ADMIN"); // Optional: Add more roles if needed
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleDao.findByRoleName(roleName) == null) {
            Role role = new Role();
            role.setRoleName(roleName);
            roleDao.save(role);
            log.info("Default role '{}' created.", roleName);
        } else {
            log.info("Role '{}' already exists.", roleName);
        }
    }
}
