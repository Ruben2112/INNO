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
    private int inviteCode;
    private int id;
    private int profileId;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.inviteCode = 0;
        this.invitedUsers = new ArrayList<>();

        this.inviteCode = generateInviteCode();
    }
    public int getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public int getInviteCode() {
        return this.inviteCode;
    }
    public int getProfileId() {
        return this.profileId;
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
        return false;
    }

    public boolean register(int inviteCode, String username, String password) {
        if(username.isEmpty() || password.isEmpty() || inviteCode == 0) {
            return false;
        }
        //boolean result = HTTPManager.doPost("user", username, password);
        return false;
    }

    public Collection<User> getInvites() {
        return this.invitedUsers;
    }

    private int generateInviteCode() {
        int[] tempArray = new int[3];
        for(int i = 3; i > 0; i--) {
            Random random = new Random();
            int n = 100000 + random.nextInt(900000);
            tempArray[i] = n;
        }

        int code = 0;
        StringBuilder strCode = null;
        for (int num : tempArray)
        {
            strCode.append(num);
        }
        code = Integer.parseInt(strCode.toString());
        return code;
    }
}
