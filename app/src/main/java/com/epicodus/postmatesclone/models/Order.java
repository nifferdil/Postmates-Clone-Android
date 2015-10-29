package com.epicodus.postmatesclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Guest on 10/28/15.
 */
@Table (name = "Orders", id = "_id")
public class Order extends Model {
    @Column(name = "Content")
    private String mContent;

    @Column(name = "CreatedAt")
    private long mCreatedAt;

    @Column(name = "User")
    private User mUser;

    public Order() {
        super();
    }

    public Order(String orderContent, User user) {
        mContent = orderContent;
        mCreatedAt = new Date().getTime();
        mUser = user;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String orderContent) {
        mContent = orderContent;
    }

    public long getCreatedAt() {
        return mCreatedAt;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d 'at' h:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("America/Los Angeles"));
        return formatter.format(mCreatedAt);
    }

    public static List<Order> all() {
        return new Select()
                .from(Order.class)
                .execute();
    }
}