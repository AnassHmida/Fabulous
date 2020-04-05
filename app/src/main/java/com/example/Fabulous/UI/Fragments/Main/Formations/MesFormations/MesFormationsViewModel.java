package com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations;


import android.content.Intent;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginActivity;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginResource;
import com.example.Fabulous.UI.Activities.SplashScreenActivity.SplashScreenResource;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.paperdb.Paper;

public class MesFormationsViewModel extends ViewModel {
    @Inject
    SessionManager sessionManager;

    private final MutableLiveData<MesFormationsResource<ProfileDetails>> mutableLiveData = new MutableLiveData();


    public LiveData<MesFormationsResource<ProfileDetails>> observeState(){
        return (LiveData)mutableLiveData;
    }

    @Inject
    public MesFormationsViewModel(){

        ProfileDetails element= Paper.book().read("loggedmodel");


    mutableLiveData.postValue(MesFormationsResource.done(element));

    //    mutableLiveData.postValue(MesFormationsResource.error("error",(ProfileDetails) null));

    }







}