package com.jsf_jpa.entity;


import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "userName", nullable = false)
    private String userName;


    public User() {
    }

    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public User(User user) {
        this.id = user.id;
        this.userName = user.userName;
        this.email = user.email;
        this.password = user.password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
