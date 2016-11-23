package com.reginalddc.teamderapp.Model;

import org.json.JSONObject;

/**
 * Created by reginalddc on 19/11/2016.
 */
public class OtherProfile {

    private JSONObject obj;
    private static String userID;
    private static String password;
    private static String fullName;
    private static String email;
    private static String  phoneNumber;
    private static String  birthday;
    private static String  school;
    private static String  course;
    private static String  yearLevel;
    private static String  mainRole;
    private static String  otherRole;
    private static String  achievements;
    private static String  extraCo;

    public OtherProfile(){}

    public OtherProfile(JSONObject obj){
        this.obj = obj;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        OtherProfile.userID = userID;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        OtherProfile.fullName = fullName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        OtherProfile.email = email;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        OtherProfile.phoneNumber = phoneNumber;
    }

    public static String getBirthday() {
        return birthday;
    }

    public static void setBirthday(String birthday) {
        OtherProfile.birthday = birthday;
    }

    public static String getSchool() {
        return school;
    }

    public static void setSchool(String school) {
        OtherProfile.school = school;
    }

    public static String getCourse() {
        return course;
    }

    public static void setCourse(String course) {
        OtherProfile.course = course;
    }

    public static String getYearLevel() {
        return yearLevel;
    }

    public static void setYearLevel(String yearLevel) {
        OtherProfile.yearLevel = yearLevel;
    }

    public static String getMainRole() {
        return mainRole;
    }

    public static void setMainRole(String mainRole) {
        OtherProfile.mainRole = mainRole;
    }

    public static String getAchievements() {
        return achievements;
    }

    public static void setAchievements(String achievements) {
        OtherProfile.achievements = achievements;
    }

    public static String getExtraCo() {
        return extraCo;
    }

    public static void setExtraCo(String extraCo) {
        OtherProfile.extraCo = extraCo;
    }

    public static String getOtherRole() {
        return otherRole;
    }

    public static void setOtherRole(String otherRole) {
        OtherProfile.otherRole = otherRole;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        OtherProfile.password = password;
    }

    public void retrievalData(){
        try{
            JSONObject object = obj.getJSONObject("profile");
            fullName = object.getString("name");
            email = object.getString("email");
            password = object.getString("password");

            if(object.getString("phone").equals("") || (object.getString("phone").equals("null"))) {
                phoneNumber = "";
            }else{
                phoneNumber = object.getString("phone");
            }

            if(!object.getString("birthday").equals("null")) {
                birthday = object.getString("birthday");
            }else { birthday = ""; }

            if(!object.getString("school").equals("null")) {
                school = object.getString("school");
            }else { school = ""; }

            if(!object.getString("course").equals("null")) {
                course = object.getString("course");
            }else { course = ""; }

            if(!object.getString("year_level").equals("null")) {
                yearLevel = object.getString("year_level");
            }else { yearLevel = ""; }

            if(!object.getString("main_role").equals("null")) {
                mainRole = object.getString("main_role");
            }else { mainRole = ""; }

            if(!object.getString("achievements").equals("null")) {
                achievements = object.getString("achievements");
            }else { achievements = ""; }

            if(!object.getString("extracuricular").equals("null")) {
                extraCo = object.getString("extracuricular");
            }else { extraCo = ""; }

            if(!object.getString("other_roles").equals("null")) {
                otherRole = object.getString("other_roles");
            }else { otherRole = ""; }

        }catch(Exception e){}
    }
}
