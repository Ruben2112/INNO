package com.inno.bourdrbij.models;

import com.inno.bourdrbij.servercommunication.HTTPManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by sebas on 6/10/2016.
 */
public class User {
    private String username;
    private String password;
    private Collection<User> invitedUsers;
    private int[] inviteCodes;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.inviteCodes = new int[3];
        this.invitedUsers = new ArrayList<>();

        this.inviteCodes = generateInviteCodes();
    }

    public boolean login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            return false;
        }
        if(this.username.equals(username) && this.password.equals(password)) {
            HTTPManager.doGet("user");
            return true;
        }
        return false;
    }

    public boolean register(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            return false;
        }
        //boolean result = HTTPManager.doPost("user", username, password);
        return true;
    }

    public boolean register(int inviteCode, String username, String password) {
        return false;
    }

    public Collection<User> getInvites() {
        return this.invitedUsers;
    }

    private int[] generateInviteCodes() {
        int[] tempArray = new int[3];
        for(int i = 3; i > 0; i--) {
            Random random = new Random();
            int n = 100000 + random.nextInt(900000);
            tempArray[i] = n;
        }
        return tempArray;
    }
}
