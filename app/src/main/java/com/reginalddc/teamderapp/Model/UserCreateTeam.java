package com.reginalddc.teamderapp.Model;

import org.json.JSONObject;

/**
 * Created by reginalddc on 11/22/2016.
 */
public class UserCreateTeam {

    private JSONObject obj;
    private static int numOfMembers;

    public UserCreateTeam() {}

    public UserCreateTeam(JSONObject obj) {this.obj = obj;}

    public static int getNumOfMembers() {
        return numOfMembers;
    }

    public static void setNumOfMembers(int numOfMembers) {
        UserCreateTeam.numOfMembers = numOfMembers;
    }
}
