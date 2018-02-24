package com.jsf_jpa.service;

import com.jsf_jpa.entity.User;
import com.jsf_jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findOneByEmailPassword(String email, String password) {
        return userRepository.findOneByEmailPassword(email, password);
    }
}
