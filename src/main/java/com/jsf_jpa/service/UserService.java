package com.jsf_jpa.service;

import com.jsf_jpa.entity.User;

public interface UserService {
    public User create(User user);

    public void delete(User user);

    public User findOneByEmailPassword(String email, String password);
}
