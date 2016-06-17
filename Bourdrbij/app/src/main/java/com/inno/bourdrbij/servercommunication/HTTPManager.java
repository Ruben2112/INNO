package com.inno.bourdrbij.servercommunication;

import com.inno.bourdrbij.models.Encounter;
import com.inno.bourdrbij.models.Event;
import com.inno.bourdrbij.models.Job;
import com.inno.bourdrbij.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sjorsvanmierlo on 03-06-16.
 */
public class HTTPManager {
    static Object result;
    static Object postResult;
    public static Object doGet(final String url) {
        //Object result = null;
        HTTPRestClient.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                try {
                    System.out.println("ARRAY");

                    if (url.contains("user")) {

                    }

                    if (url.contains("profile")) {
                        JSONObject jsonProfile = (JSONObject) response.get(0);
                        JSONObject data = (JSONObject) jsonProfile.get("data");

                        int pId = (Integer) data.getInt("id");
                        String pName = (String) data.getString("name");
                        //String pProfilePicture = (String)data.getString("profilePicture");
                        String pProffesion = (String) data.getString("proffesion");
                        String[] pInterests = data.getString("interests").split(";");

                        JSONObject address = (JSONObject) data.get("get_address");
                        String pAddress = address.getString("street") + " " + address.getString("number") + ", " + address.getString("city") + ", " + address.getString("country");

                        Profile profile = new Profile(pId, pName, pProffesion, pAddress, pInterests);
                        result = (Object) null;
                        result = profile;
                    }

                    if (url.contains("job")) {
                        JSONObject jsonJob = (JSONObject) response.get(0);
                        JSONObject data = (JSONObject) jsonJob.get("data");

                        int pId = (Integer) data.getInt("id");
                        String pName = (String) data.getString("name");
                        int pOwnerProfileId = (Integer) data.getInt("ownerProfileId");
                        int pAcceptedProfileId = (Integer) data.getInt("acceptedProfileId");
                        String pDescription = (String) data.getString("description");
                        String pReward = (String) data.getString("reward");
                        Boolean pAccepted = (Boolean) data.getBoolean("accepted");

                        Job job = new Job(pId, pOwnerProfileId, pAcceptedProfileId, pName, pDescription, pReward, pAccepted);
                        result = (Object) null;
                        result = job;
                    }

                    if (url.contains("event")) {
                        JSONObject jsonEvent = (JSONObject) response.get(0);
                        JSONObject data = (JSONObject) jsonEvent.get("data");

                        int pId = (Integer) data.getInt("id");
                        String pName = (String) data.getString("name");
                        int pOwnerProfileId = (Integer) data.getInt("ownerProfileId");
                        String pDescription = (String) data.getString("description");

                        String st = (String) data.getString("startTime");
                        String et = (String) data.getString("endTime");
                        Date pStartTime = createDateObject(st);
                        Date pEndTime = createDateObject(et);

                        JSONObject address = (JSONObject) data.get("get_address");
                        String pAddress = address.getString("street") + " " + address.getString("number") + ", " + address.getString("city") + ", " + address.getString("country");

                        //location??? HOE WELK FORMAT? < ADDRESS STRING
                        //deelnemers?? NOG EVEN FIXEN IN DBg

                        Event event = new Event(pOwnerProfileId, new ArrayList<Integer>(), pAddress, pName, pDescription, pStartTime, pEndTime);
                        result = (Object) null;
                        result = event;
                    }

                    if (url.contains("encounter")) {
                        JSONObject jsonEncounter = (JSONObject) response.get(0);
                        JSONObject data = (JSONObject) jsonEncounter.get("data");
                        int pId = (Integer) data.getInt("id");

                        JSONObject receiverProfileData = (JSONObject) data.get("get_receiver_profile");

                        int pReceiverId = (Integer) receiverProfileData.getInt("id");
                        String pReceiverName = (String) receiverProfileData.getString("name");
                        //String pReceiverProfilePicture = (String)receiverProfileData.getString("profilePicture");
                        String pReceiverProffesion = (String) receiverProfileData.getString("proffesion");
                        String[] pReceiverInterests = receiverProfileData.getString("interests").split(";");

                        JSONObject pReceiverAddressData = (JSONObject) receiverProfileData.get("get_address");
                        String pReceiverAddress = pReceiverAddressData.getString("street") + " " + pReceiverAddressData.getString("number") + ", " + pReceiverAddressData.getString("city") + ", " + pReceiverAddressData.getString("country");

                        Profile pReceiverProfile = new Profile(pReceiverId, pReceiverName, pReceiverProffesion, pReceiverAddress, pReceiverInterests);

                        JSONObject senderProfileData = (JSONObject) data.get("get_sender_profile");

                        int pSenderId = (Integer) senderProfileData.getInt("id");
                        String pSenderName = (String) senderProfileData.getString("name");
                        //String pSenderProfilePicture = (String)senderProfileData.getString("profilePicture");
                        String pSenderProffesion = (String) senderProfileData.getString("proffesion");
                        String[] pSenderInterests = senderProfileData.getString("interests").split(";");

                        JSONObject pSenderAddressData = (JSONObject) senderProfileData.get("get_address");
                        String pSenderAddress = pSenderAddressData.getString("street") + " " + pSenderAddressData.getString("number") + ", " + pSenderAddressData.getString("city") + ", " + pSenderAddressData.getString("country");

                        Profile pSenderProfile = new Profile(pSenderId, pSenderName, pSenderProffesion, pSenderAddress, pSenderInterests);

                        Date pDateTime = createDateObject((String) data.getString("datetime"));
                        Float pLatitude = Float.parseFloat((String) data.getString("latitude"));
                        Float pLongtitude = Float.parseFloat((String) data.getString("longtitude"));
                        Boolean pSenderConfirm = (Boolean) data.getBoolean("senderConfirm");
                        Boolean pReceiverConfirm = (Boolean) data.getBoolean("receiverConfirm");
                        Encounter encounter = new Encounter(pId, pReceiverProfile, pSenderProfile, pDateTime, pLongtitude, pLatitude, pSenderConfirm, pReceiverConfirm);
                        result = (Object) null;
                        result = encounter;
                    }

                    if (url.contains("chat")) {

                    }
                } catch (Exception ex) {
                    System.out.println("BOEM ALLAH HAKBAR");
                    System.out.println(ex.getMessage());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                System.out.println("OBJECT");
            }
        });

        return result;
    }

    public static Object doPost(final String url, RequestParams paramaters){
        postResult = null;

        HTTPRestClient.post(url, paramaters,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try{

                    if(url.contains("login")){
                        System.out.println("JSONArray");

                        JSONObject jsonProfile = (JSONObject)response.get(0);
                        System.out.println(jsonProfile.toString());

                    }
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

            }
        });

        return postResult;
    }

    private static Date createDateObject(String dbDate) {
        Date result = new Date();
        result.setYear(Integer.parseInt(dbDate.substring(0, 3)));
        result.setMonth(Integer.parseInt(dbDate.substring(5, 6)));
        result.setDate(Integer.parseInt(dbDate.substring(8, 9)));
        result.setHours(Integer.parseInt(dbDate.substring(11, 12)));
        result.setMinutes(Integer.parseInt(dbDate.substring(13, 14)));
        result.setSeconds(Integer.parseInt(dbDate.substring(16, 17)));
        return result;
    }
}
