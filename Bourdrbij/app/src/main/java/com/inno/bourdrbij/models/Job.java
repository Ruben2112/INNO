package com.inno.bourdrbij.models;

/**
 * Created by sebas on 5/20/2016.
 */
public class Job {

    private int id;
    private int ownerProfileID;
    private int acceptedProfileID;
    private String name;
    private String description;
    private String reward;
    private boolean accepted;

    public Job( int ownerProfileID, String name, String description, String reward) {
        this.ownerProfileID = ownerProfileID;
        this.name = name;
        this.description = description;
        this.reward = reward;
    }

    public void AcceptJob(int id, String username)
    {

    }

    public void RejectJob(int id, String username)
    {

    }

    public void RecommendFriend(String username)
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerProfileID() {
        return ownerProfileID;
    }

    public void setOwnerProfileID(int ownerProfileID) {
        this.ownerProfileID = ownerProfileID;
    }

    public int getAcceptedProfileID() {
        return acceptedProfileID;
    }

    public void setAcceptedProfileID(int acceptedProfileID) {
        this.acceptedProfileID = acceptedProfileID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
