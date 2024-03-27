package com.auth1.authlearning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

    public User(){

    }
    public User(String name, String email, String password) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.isEmailVerified = false;
        this.roles = new ArrayList<>();

    }
}
