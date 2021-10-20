package com.shah.SpringJwt.repo;

import com.shah.SpringJwt.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}