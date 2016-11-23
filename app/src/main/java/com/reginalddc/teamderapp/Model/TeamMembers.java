package com.reginalddc.teamderapp.Model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by reginalddc on 24/11/2016.
 */
public class TeamMembers {

    private JSONObject obj;
    private static String[] memberID;
    private static String[] userID;
    private static String[] role;
    private static String[] name;

    public TeamMembers() {}

    public TeamMembers(JSONObject obj) { this.obj = obj; }

    public static String[] getMemberID() {
        return memberID;
    }

    public static void setMemberID(String[] memberID) {
        TeamMembers.memberID = memberID;
    }

    public static String[] getUserID() {
        return userID;
    }

    public static void setUserID(String[] userID) {
        TeamMembers.userID = userID;
    }

    public static String[] getRole() {
        return role;
    }

    public static void setRole(String[] role) {
        TeamMembers.role = role;
    }

    public static String[] getName() {
        return name;
    }

    public static void setName(String[] name) {
        TeamMembers.name = name;
    }

    public void retrievalData(){

        try{

            JSONArray members = obj.getJSONArray("members");
            if (members.length() > 0) {
                memberID = new String[members.length()];
                userID = new String[members.length()];
                role = new String[members.length()];
                name = new String[members.length()];

                for (int i = 0; i < members.length(); i++){
                    memberID[i] = members.getJSONObject(i).getString("member_id");
                    userID[i] = members.getJSONObject(i).getString("user_id");
                    role[i] = members.getJSONObject(i).getString("role");
                    name[i] = members.getJSONObject(i).getString("name");
                }
            }

        }catch (Exception e) {}
    }
}
