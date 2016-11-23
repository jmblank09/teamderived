package com.reginalddc.teamderapp.Model;

import org.json.JSONObject;

/**
 * Created by reginalddc on 11/22/2016.
 */
public class UserCreateTeam {

    private JSONObject obj;
    private static int numOfMembers;
    private static String teamName;
    private static String teamDesc;
    private static String roles;

    public UserCreateTeam() {}

    public UserCreateTeam(JSONObject obj) {this.obj = obj;}

    public static int getNumOfMembers() {
        return numOfMembers;
    }

    public static void setNumOfMembers(int numOfMembers) {
        UserCreateTeam.numOfMembers = numOfMembers;
    }

    public static String getTeamName() {
        return teamName;
    }

    public static void setTeamName(String teamName) {
        UserCreateTeam.teamName = teamName;
    }

    public static String getTeamDesc() {
        return teamDesc;
    }

    public static void setTeamDesc(String teamDesc) {
        UserCreateTeam.teamDesc = teamDesc;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        UserCreateTeam.roles = roles;
    }
}

