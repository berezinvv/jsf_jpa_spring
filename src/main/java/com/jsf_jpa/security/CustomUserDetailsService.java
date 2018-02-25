package com.jsf_jpa.security;

import com.jsf_jpa.entity.Role;
import com.jsf_jpa.entity.User;
import com.jsf_jpa.repository.RoleRepository;
import com.jsf_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+username+" not found");
        }
        List<String> userRoles = new LinkedList<>();
        for(Role role:user.getRoles()){
            userRoles.add(role.getRole());
        }
        return new CustomUserDetails(user, userRoles);
    }

}
