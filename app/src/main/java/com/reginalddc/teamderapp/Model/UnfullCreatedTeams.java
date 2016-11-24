package com.reginalddc.teamderapp.Model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by masterpeps on 11/23/2016.
 */
public class UnfullCreatedTeams {
    private int teamId;
    private String teamName;
    private String teamDescription;
    private String teamLeader;
    private int capacity;
    private String [] roles = {"","","","","",""};
    private String [] members = {"", "", "", "","",""};

    public void setTeamId(int teamId){
        this.teamId = teamId;
    }
    public void setTeamLeader(String teamLeader){ this.teamLeader = teamLeader;}
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
    public String getTeamLeader(){
        return teamLeader;
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
            String[] role2;
            teamId = object.getInt("team_id");
            teamName = object.getString("name");
            teamLeader = object.getString("leader_name");
            teamDescription = object.getString("description");
            capacity = object.getInt("capacity");
            String roles2 = object.getString("roles");
            role2 = roles2.split(";");
            roles[0] = "Team-Leader";
            for(int i = 0; i <= role2.length; i++){
                roles[i + 1] = role2[i];
            }
            System.out.println("gago");
            invokeWs2();

        }catch (Exception ex){}
    }
    private void invokeWs2(){

        AsyncHttpClient team = new AsyncHttpClient();
        final RequestParams params = new RequestParams();
        params.put("team_id",Integer.toString(getTeamId()));

        team.get("http://107.170.61.180/android/teamderived_api/members/get_members.php",params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response){
                try{
                    JSONArray json2 = (new JSONObject(response)).getJSONArray("members");
                    for(int i = 0; i< json2.length();i++){
                        System.out.println((json2.getJSONObject(i)).getString("name"));
                        for(int j = 0; j < capacity; j++){
                            if((json2.getJSONObject(i)).getString("role").equals(roles[j])){
                                members[j] = (json2.getJSONObject(i)).getString("name");
                            }else{
                                members[j] = "None";
                            }
                        }
                    }
                }catch(Exception e){}
            }
        });
    }
}
