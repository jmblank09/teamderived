package com.reginalddc.teamderapp.Model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by reginalddc on 23/11/2016.
 */
public class    UserTeam {

    private JSONObject obj;
    private static String[] teamID;
    private static String[] teamName;
    private static String[] teamDescription;
    private static String selectedTeamID;
    private static String selectedTeamName;
    private static String selectedTeamDescription;

    public UserTeam() {}

    public UserTeam(JSONObject obj) { this.obj = obj; }

    public static String[] getTeamID() {
        return teamID;
    }

    public static void setTeamID(String[] teamID) {
        UserTeam.teamID = teamID;
    }

    public static String[] getTeamName() {
        return teamName;
    }

    public static void setTeamName(String[] teamName) {
        UserTeam.teamName = teamName;
    }

    public static String[] getTeamDescription() {
        return teamDescription;
    }

    public static void setTeamDescription(String[] teamDescription) {
        UserTeam.teamDescription = teamDescription;
    }

    public static String getSelectedTeamID() {
        return selectedTeamID;
    }

    public static void setSelectedTeamID(String selectedTeamID) {
        UserTeam.selectedTeamID = selectedTeamID;
    }

    public static String getSelectedTeamName() {
        return selectedTeamName;
    }

    public static void setSelectedTeamName(String selectedTeamName) {
        UserTeam.selectedTeamName = selectedTeamName;
    }

    public static String getSelectedTeamDescription() {
        return selectedTeamDescription;
    }

    public static void setSelectedTeamDescription(String selectedTeamDescription) {
        UserTeam.selectedTeamDescription = selectedTeamDescription;
    }

    public void retrievalData(){

        try{
            JSONArray teams = obj.getJSONArray("teams");
            teamID = new String[0];
            teamName = new String[0];
            teamDescription = new String[0];

            if (teams.length() > 0) {
                teamID = new String[teams.length()];
                teamName = new String[teams.length()];
                teamDescription = new String[teams.length()];
                for (int i = 0; i < teams.length(); i++){
                    teamID[i] = teams.getJSONObject(i).getString("team_id");
                    teamName[i] = teams.getJSONObject(i).getString("name");
                    teamDescription[i] = teams.getJSONObject(i).getString("description");
                }
            }

        }catch (Exception e){}
    }

    public void retrievalData2(){

        try{
            selectedTeamName = obj.getJSONObject("team_info").getString("name");
            selectedTeamDescription = obj.getJSONObject("team_info").getString("description");

        }catch (Exception e){}
    }
}
