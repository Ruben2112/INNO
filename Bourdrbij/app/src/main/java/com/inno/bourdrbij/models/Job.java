package com.inno.bourdrbij.models;

import java.io.Serializable;

/**
 * Created by sebas on 5/20/2016.
 */
public class Job implements Serializable {

    private int id;
    private int ownerProfileId;
    private int acceptedProfileId;
    private String title;
    private String description;
    private String reward;
    private boolean accepted;

    public Job(int id, int ownerProfileId, int acceptedProfileId, String title, String description, String reward) {
        this.id = id;
        this.ownerProfileId = ownerProfileId;
        this.acceptedProfileId = acceptedProfileId;
        this.title = title;
        this.description = description;
        this.reward = reward;
    }

    public Job(int id, int ownerProfileId, int acceptedProfileId, String title, String description, String reward, boolean accepted) {
        this.id = id;
        this.ownerProfileId = ownerProfileId;
        this.acceptedProfileId = acceptedProfileId;
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.accepted = accepted;
    }

    public Job( int ownerProfileID, String name, String description, String reward) {
        this.ownerProfileId = ownerProfileID;
        this.title = name;
        this.description = description;
        this.reward = reward;
    }

    public void AcceptJob(int id, String username){}

    {

    }

    public void RejectJob(int id, String username)
    {

    }

    public void NewJob(int userId, String title, String Description, String reward)
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerProfileID() {
        return ownerProfileId;
    }

    public void setOwnerProfileID(int ownerProfileID) {
        this.ownerProfileId = ownerProfileID;
    }

    public int getAcceptedProfileID() {
        return acceptedProfileId;
    }

    public void setAcceptedProfileID(int acceptedProfileID) {
        this.acceptedProfileId = acceptedProfileID;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
