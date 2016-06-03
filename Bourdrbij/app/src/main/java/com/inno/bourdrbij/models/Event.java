package com.inno.bourdrbij.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sebas on 5/20/2016.
 */
public class Event {

    private int id;
    private int ownerProfileID;
    private ArrayList<Integer> participants;
    private String location;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;

    public Event(int ownerProfileID, ArrayList<Integer> participants, String location, String name, String description, Date startTime, Date endTime ) {
        this.ownerProfileID = ownerProfileID;
        this.participants = participants;
        this.location = location;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addFriends(ArrayList<Integer> friendIDs)
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

    public ArrayList<Integer> getParticipants() { return participants; }

    public void setParticipants(ArrayList<Integer> participants) { this.participants = participants; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getAddressid() {
        return location;
    }

    public void setAddressid(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) { this.endTime = endTime; }
}
