package com.example.Fabulous.Utils.Parser;


import com.example.Fabulous.Models.ProfileDetails;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.ArrayList;


public class ProfileDetailsParser {

    public static String getIDfromResponse(JsonElement element) {

        Gson gson = new GsonBuilder().create();
        ProfileDetails r = gson.fromJson(element, ProfileDetails.class);
        return r.token.id;
    }
    public static ArrayList<ProfileDetails.Training> getTrainingsfromResponse(JsonElement element) {
        Gson gson = new GsonBuilder().create();
        ProfileDetails  r = gson.fromJson(element, ProfileDetails.class);
       return r.profile.trainings.trainings;

    }
    public static ProfileDetails getProfileDetailsfromResponse(JsonElement element) {

        Gson gson = new GsonBuilder().create();
        ProfileDetails r = gson.fromJson(element, ProfileDetails.class);
        return r;
    }

    public static Integer getTTLfromResponse(JsonElement element) {

        Gson gson = new GsonBuilder().create();
        ProfileDetails r = gson.fromJson(element, ProfileDetails.class);
        return r.token.ttl;
    }
}
