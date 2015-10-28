package com.epicodus.postmatesclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by Guest on 10/28/15.
 */
@Table (name = "Users", id = "_id")
public class User extends Model {
    @Column (name = "Username")
    private String mUsername;

    @Column (name = "Password")
    private String mPassword;

    public User() {
        super();
    }

    public User(String usernmame, String password) {
        mUsername = usernmame;
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public static User find(String username, String password) {
        return new Select()
                .from(User.class)
                .where("Username = ?", username)
                .where("Password = ?", password)
                .executeSingle();
    }
}
