package com.example.Fabulous.UI.Activities.SplashScreenActivity;

import android.content.Intent;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginActivity;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginResource;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class SplashScreenViewModel extends ViewModel {
    private Handler mHandler = new Handler();
    private final MutableLiveData mutableLiveData = new MutableLiveData();





    public LiveData<SplashScreenResource> observeState(){
        return (LiveData)mutableLiveData;
    }

    @Inject
    public SplashScreenViewModel(){

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mutableLiveData.postValue(SplashScreenResource.done("done"));
            }
        }, 2000);


        }







}