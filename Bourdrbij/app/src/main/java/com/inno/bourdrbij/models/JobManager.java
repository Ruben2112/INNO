package com.inno.bourdrbij.models;

import java.util.ArrayList;

/**
 * Created by Jurgen on 6/2/2016.
 */
public class JobManager {

    public static ArrayList<Job> GetAll()
    {
        ArrayList<Job> jobs = new ArrayList<>();

        return jobs;
    }

    public static void GetLast(int count)
    {
        //TODO: GetLast als in meest recent waarin count aantal is?
    }

    public static Job NewJob(int usernameID, String name, String description, String reward)
    {
        return new Job(usernameID, name, description, reward);
    }

    public static void DeleteJob(int id)
    {

    }

    public static void UpdateJob(int id, String description, String reward)
    {

    }

    public static void SearchJob(String searchCriteria)
    {

    }
}
