package com.jsf_jpa.repository;

import com.jsf_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);

    User findOneByEmail(String email);

    @Query("select u from User u where u.email=:email and u.password=:password")
    User findOneByEmailPassword(@Param("email") String email, @Param("password") String password);
}
