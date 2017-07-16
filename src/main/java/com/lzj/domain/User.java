package com.lzj.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by li on 17-7-16.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String passward;
    private String email;
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassward() {
        return passward;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passward='" + passward + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
