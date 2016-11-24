package com.reginalddc.teamderapp.Model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by reginalddc on 24/11/2016.
 */
public class TeamRequests {

    private JSONObject obj;
    private static String[] requestID;
    private static String[] userID;
    private static String[] role;
    private static String[] name;

    public TeamRequests() {}

    public TeamRequests(JSONObject obj) { this.obj = obj; }

    public static String[] getRequestID() {
        return requestID;
    }

    public static void setRequestID(String[] requestID) {
        TeamRequests.requestID = requestID;
    }

    public static String[] getUserID() {
        return userID;
    }

    public static void setUserID(String[] userID) {
        TeamRequests.userID = userID;
    }

    public static String[] getRole() {
        return role;
    }

    public static void setRole(String[] role) {
        TeamRequests.role = role;
    }

    public static String[] getName() {
        return name;
    }

    public static void setName(String[] name) {
        TeamRequests.name = name;
    }

    public void retrievalData() {

        try{

            JSONArray requests = obj.getJSONArray("requests");
            requestID = new String[0];
            userID = new String[0];
            role = new String[0];
            name = new String[0];

            if (requests.length() > 0) {
                requestID = new String[requests.length()];
                userID = new String[requests.length()];
                role = new String[requests.length()];
                name = new String[requests.length()];

                for (int i = 0; i < requests.length(); i++) {
                    requestID[i] = requests.getJSONObject(i).getString("request_id");
                    userID[i] = requests.getJSONObject(i).getString("user_id");
                    role[i] = requests.getJSONObject(i).getString("role");
                    name[i] = requests.getJSONObject(i).getString("name");
                }
            }

        }catch (Exception e) {}
    }
}
