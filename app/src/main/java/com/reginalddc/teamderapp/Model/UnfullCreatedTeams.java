package com.reginalddc.teamderapp.Model;

import org.json.JSONObject;

/**
 * Created by masterpeps on 11/23/2016.
 */
public class UnfullCreatedTeams {
    private int teamId;
    private String teamName;
    private String teamDescription;
    private int capacity;
    private String [] roles;
    private String [] members;

    public void setTeamId(int teamId){
        this.teamId = teamId;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public void setTeamDescription(String teamDescription){
        this.teamDescription = teamDescription;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setRoles(String [] roles){
        this.roles = roles;
    }

    public void setMembers(String [] members) { this.members = members;}

    public int getTeamId(){
        return teamId;
    }

    public String getTeamName(){
        return teamName;
    }

    public String getTeamDescription(){
        return teamDescription;
    }

    public int getCapacity(){
        return capacity;
    }

    public String[] getRoles(){
        return roles;
    }

    public String[] getMembers() { return members; }

    public void retrievalData(JSONObject object){
        try{
            teamId = object.getInt("team_id");
            teamName = object.getString("name");
            teamDescription = object.getString("description");
            capacity = object.getInt("capacity");
            String roles2 = object.getString("roles");
            roles = roles2.split(";");
        }catch (Exception ex){}
    }
}
