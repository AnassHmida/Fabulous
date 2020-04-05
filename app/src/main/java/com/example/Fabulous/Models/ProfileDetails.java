package com.example.Fabulous.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class ProfileDetails {
        @SerializedName("token")
        public token token;

        public class token {
            @SerializedName("id")
            public String id;
            @SerializedName("ttl")
            public Integer ttl;

        }
        @SerializedName("role")
        public  Role role;

        public class Role {
            @SerializedName("description")
            public String description;
            public String getDescription() {
                return description;
            }
        }


        @SerializedName("Home")
        public  Profile profile;




        public class Profile {

            @SerializedName("Profile")
            public Details details;
            @SerializedName("Trainings")
            public Trainings trainings;

        }
        public class Details {
            @SerializedName("avatarURL")
            public String avatarUrl;
            @SerializedName("name")
            public String name;

        }



    public static class Trainings{
        @SerializedName("Trainings")
        public ArrayList<Training> trainings;


    }

    @SuppressWarnings("serial")

    public static class Training implements Serializable {
        @SerializedName("name")
        public String name;
        @SerializedName("description")
        public String description;
        @SerializedName("trainingId")
        public String id;
        @SerializedName("coverURL")
        public String coverUrl;
        @SerializedName("consentExplication")
        public String consentExplication;
        @SerializedName("detailsURL")
        public String detailsURL;
        @SerializedName("Sections")
        public ArrayList<Section> sections;
    }
        public static class Section implements Serializable {
            @SerializedName("sectionTitle")
            public String sectionTitle;
            @SerializedName("Courses")
            public ArrayList<Course> courses;
        }
        public static class Course implements Serializable {
            @SerializedName("title")
            public String title;
            @SerializedName("videoId")
            public String videoId;


        }

    public ProfileDetails() {
    }

    public ProfileDetails(ProfileDetails.token token, Role role, Profile profile) {
        this.token = token;
        this.role = role;
        this.profile = profile;
    }

    public ProfileDetails.token getToken() {
        return token;
    }

    public void setToken(ProfileDetails.token token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
