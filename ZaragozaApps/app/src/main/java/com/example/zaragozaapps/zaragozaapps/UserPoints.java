package com.example.zaragozaapps.zaragozaapps;

/**
 * Created by alber on 07/11/2015.
 */
public class UserPoints {

    public String user;
    public String points;

    public String getPoints() {
        return points;
    }

    public String getUser() {

        return user;
    }

    public UserPoints(String user, String points) {

        this.user = user;
        this.points = points;
    }
}
