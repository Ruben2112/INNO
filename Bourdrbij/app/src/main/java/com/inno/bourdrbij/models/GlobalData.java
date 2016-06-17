package com.inno.bourdrbij.models;

/**
 * Created by sebas on 6/17/2016.
 */
public class GlobalData {
    private static GlobalData globalData;
    private User user;
    private Profile userProfile;

    public Profile getUserProfile() {
        return this.userProfile;
    }
    public User getUser() {
        return this.user;
    }
    public  void setUserProfile(Profile profile) {
        this.userProfile = profile;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public GlobalData() {
        this.userProfile = null;
        this.user = null;
    }
    public static GlobalData create() {
        if(globalData == null) {
            globalData = new GlobalData();
        }
        return globalData;
    }
}
