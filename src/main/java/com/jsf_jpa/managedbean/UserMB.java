package com.jsf_jpa.managedbean;

import com.jsf_jpa.entity.Role;
import com.jsf_jpa.entity.User;
import com.jsf_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.Set;

@Component("UserMB")
@Scope("session")
public class UserMB {

    private String email;
    private String password;
    private String userName;

    @Autowired
    private UserService userService;

    public UserMB() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public String signIn(UserMB userMB) {
//        User user = userService.findOneByEmailPassword(userMB.email, userMB.password);
//        if (user == null) {
//            user = new User();
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!",
//                            " Login Error!"));
//            return null;
//        } else {
//            return "main.xhtml";
//        }
//
//    }

    public String registration(){
        return "registration.xhtml";
    }

    public String createUser(UserMB userMB){
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
        role.setRole("USER");
        roleSet.add(role);
        User user = new User(userMB.email, userMB.password, userMB.userName, roleSet);
        try {
            userService.create(user);
            return "login.xhtml";
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email, password or user name is not correct!",
                            " Registration Error!"));
            return "registration.xhtml";
        }

    }
}
