package com.group4.group4.repository;

import com.group4.group4.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {
}
