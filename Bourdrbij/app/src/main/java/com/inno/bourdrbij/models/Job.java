package com.inno.bourdrbij.models;

/**
 * Created by sebas on 5/20/2016.
 */
public class Job {

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

    public int getId() {
        return this.id;
    }



    public void GetAll()
    {

    }

    public void GetLast(int count)
    {

    }

    public void NewJob(int userId, String title, String Description, String reward)
    {

    }

    public void DeleteJob(int id)
    {

    }

    public void UpdateJob(int id, String description, String reward)
    {

    }

    public void SearchJob(String searchCriteria)
    {

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
}
