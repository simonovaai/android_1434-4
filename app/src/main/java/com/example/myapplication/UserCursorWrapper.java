package com.example.myapplication;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.myapplication.database.UserDbSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDbSchema.UserTable.cols.UUID));
        String userName = getString(getColumnIndex(UserDbSchema.UserTable.cols.USERNAME));
        String userLastname = getString(getColumnIndex(UserDbSchema.UserTable.cols.USERLASTNAME));

        User user = new User(UUID.fromString(uuidString));
        user.setUserName(userName);
        user.setUserLastName(userLastname);
        return user;
    }
}
