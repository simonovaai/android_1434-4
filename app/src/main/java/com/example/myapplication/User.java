package com.example.myapplication;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String userName;
    private String userLastName;
    private UUID uuid;

    public User() {// случайный номер id пользователя
        this(UUID.randomUUID()); // при создании нового пользователя
    }
    public User(UUID uuid){
        this.uuid = uuid; // при создании существующего пользователя
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public UUID getUuid() {
        return uuid;
    }
}
