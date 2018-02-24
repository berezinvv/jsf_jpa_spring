package com.jsf_jpa.repository;


import com.jsf_jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select a.role from Role a, User b where b.userName=?1 and a.userid=b.id")
    public List<String> findRoleByUserName(String username);
}
