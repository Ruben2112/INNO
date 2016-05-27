package com.inno.bourdrbij.models;

import java.util.ArrayList;
import java.util.Collection;

import com.inno.bourdrbij.servercommunication.HTTPRestClient;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sebas on 5/20/2016.
 */
public class Profile {
    public static Profile retrievedProfile;
    public static Profile thisProfile;
    // Values
    private String username;
    private String profession;
    private String[] intrests;
    private String address;
    private String picturePath;
    private int id;

    // List elements
    private Collection<Event> eventsList;
    private Collection<Job> jobsList;
    private Collection<Encounter> encountersList;
    private Collection<Profile> friendsList;

    /**
     * Constructs a Profile object.
     * Initializes list elements.
     * Initializes values as null.
     */
    public Profile() {
        this.intrests = new String[5];
        this.eventsList = new ArrayList<>();
        this.jobsList = new ArrayList<>();
        this.encountersList = new ArrayList<>();
        this.friendsList = new ArrayList<>();
        this.username = null;
        this.profession = null;
        this.address = null;
        this.picturePath = null;
        this.id = 0;
        thisProfile = this;
        this.retrievedProfile = null;
    }

    /**
     * Constructs a Profile object.
     * @param username Initializes the username of the retrievedProfile. Parameter can not be null or empty.
     * @param profession Initializes the profession of the retrievedProfile. Parameter can not be null or empty.
     * @param address Initializes the username of the retrievedProfile. Parameter can not be null or empty.
     */
    public Profile(String username, String profession, String address, String[] intrests) {
        this();
        this.changeUsername(username);
        this.changeProfession(profession);
        this.changeAddress(address);
        this.changeIntrests(intrests);

        thisProfile = this;
    }

    public Profile(int id, String username, String profession, String address, String[] intrests) {
        this(username, profession, address, intrests);
        this.changeId(id);

        thisProfile = this;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Retrieve retrievedProfile from server.
     * @param id
     */
    public Profile getProfile(int id) {
        HTTPRestClient.get("retrievedProfile/" + id, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        JSONObject json = response.getJSONObject("data");
                        String username = json.getString("username");
                        String profession = json.getString("profession");
                        String address = json.getString("address");
                        JSONArray intrestsJsonArray = json.getJSONArray("intrests");
                        String[] intrests = new String[intrestsJsonArray.length()];
                        for (int i = 0; i < intrestsJsonArray.length(); i++) {
                            intrests[i] = intrestsJsonArray.getString(i);
                        }
                        int id = json.getInt("id");
                        // String picturePath = json.getString("picture");

                        Profile.retrievedProfile = new Profile(id, username, profession, address, intrests);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        return Profile.retrievedProfile;
    }

    /**
     * Edit the retrievedProfile with values given by the parameters.
     * @param username Changes the username of the retrievedProfile. Parameter can not be null or empty.
     * @param profession Changes the profession of the retrievedProfile. Parameter can not be null or empty.
     * @param intrests Changes the intrests of the retrievedProfile. Parameter can not be null or empty. The new intrests will overwrite the current intrests.
     * @param address Changes the username of the retrievedProfile. Parameter can not be null or empty.
     */
    public void editProfile(String username, String profession, String[] intrests, String address) {
        this.changeUsername(username);
        this.changeProfession(profession);
        this.changeIntrests(intrests);
        this.changeAddress(address);

        RequestParams params = new RequestParams();

        params.put("id", this.getId());
        params.put("username", username);
        params.put("profession", profession);
        params.put("intrests", intrests);
        params.put("address", address);

        HTTPRestClient.put("profile/" + retrievedProfile.getId() + "/update", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }



    /**
     * Search for profiles in server.
     * @param searchCriteria
     */
    public void searchProfile(String searchCriteria) {

    }

    /**
     * Edit the retrievedProfile picture.
     * Send new retrievedProfile picture to the server for saving.
     * @param picturePath Changes the picture of the retrievedProfile. Parameter can not be null or empty.
     */
    public void uploadPicture(String picturePath) {
        if(picturePath != null && !picturePath.isEmpty()) {
            this.picturePath = picturePath;
            RequestParams params = new RequestParams();

            params.put("id", this.getId());
            params.put("profilePicture", picturePath);

            HTTPRestClient.put("profile/" + retrievedProfile.getId() + "/update", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }

    /**
     * Remove picture from server.
     * @param username Username of the retrievedProfile whose picture has to be removed.
     * @param pictureID ID in database.
     */
    public void removePicture(String username, int pictureID) {
        // TODO: Make query call to remove the picture with parameter pictureId from the retrievedProfile whose username is the parameter username.
    }

    /**
     * Add friend to the retrievedProfile on the server.
     * Retrieve and reinitialize new friends list.
     * @param id Id of the retrievedProfile which we like to add to our friends list.
     */
    public void addFriend(int id) {
        // TODO: Make query call to add retrievedProfile to friends list in the database on the server.

        this.friendsList = this.retrieveFriendsList(id);
    }

    public void addFriendLocal(Profile profile) {
        this.friendsList.add(profile);
    }
    /**
     * Delete a friend from the retrievedProfile on the server.
     * Retrieve and reinitialize new friends list.
     * @param id Id of the retrievedProfile which we like to remove from our friends list.
     */
    public void deleteFriend(int id) {
        // TODO: Make query call to remove retrievedProfile from the friends list in the database on the server.

        this.friendsList = this.retrieveFriendsList(id);
    }

    public void getFriends(int id) {
        this.retrieveFriendsList(id);
    }

    /**
     * Build heatmap from friends list according to their addresses.
     * @param username Username of the retrievedProfile whose heat map we want to see.
     */
    public void getMap(String username) {
        // TODO: Make query call to retrieve addresses of the friends of the retrievedProfile with parameter username.
        Collection<String> addressesOfFriends = new ArrayList<>();

    }

    private void changeUsername(String username) {
        if(username != null && !username.isEmpty()) {
            this.username = username;
        }
    }
    private void changeProfession(String profession) {
        if(profession != null && !profession.isEmpty()) {
            this.profession = profession;
        }
    }
    private void changeIntrests(String[] intrests) {
        if(intrests != null && intrests.length > 0) {
            this.intrests = intrests;
        }
    }
    private void changeAddress(String address) {
        if(address != null && !address.isEmpty()) {
            this.address = address;
        }
    }
    private void changeId(int id) {
        this.id = id;
    }

    /**
     * Retrieve the friends of this retrievedProfile from the database on the server.
     * @return Friends list.
     */
    private Collection<Profile> retrieveFriendsList(int id) {
        Collection<Profile> friendsList = new ArrayList<>();

        HTTPRestClient.get("user/" + id + "/friends", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getBoolean("succes") == true) {
                        JSONArray allFriendsJSON = response.getJSONArray("user"); // TODO: Vragen naar de juiste naam van het element aan Sjors.
                        for(int i = 0; i < allFriendsJSON.length(); i++) {
                            JSONObject profileJSON = allFriendsJSON.getJSONObject(i);
                            String username = profileJSON.getString("username");
                            String profession = profileJSON.getString("profession");
                            String address = profileJSON.getString("address");
                            JSONArray intrestsJsonArray = profileJSON.getJSONArray("intrests");
                            String[] intrests = new String[intrestsJsonArray.length()];
                            for(int j = 0; j < intrestsJsonArray.length(); j++) {
                                intrests[j] = intrestsJsonArray.getString(j);
                            }
                            int id = profileJSON.getInt("id");
                            // String picturePath = json.getString("picture");
                            Profile friend = new Profile(username, profession, address, intrests);
                            Profile.thisProfile.addFriendLocal(friend);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return friendsList;
    }

}
