package com.example.Fabulous.UI.Fragments.Main.Actualites.List;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;


import com.example.Fabulous.Models.ActualiteDetails;
import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.Network.Actualite.List.ActualiteService;
import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginResource;
import com.example.Fabulous.UI.Fragments.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ActualiteViewModel extends ViewModel {
    private static final String TAG = "ActualiteViewModel";

    private final SessionManager sessionManager;
    private final ActualiteService ActualiteAPI;

    @Inject
    public  ActualiteViewModel(ActualiteService ActualiteAPI, SessionManager sessionManager){
        this.sessionManager = sessionManager;
        this.ActualiteAPI = ActualiteAPI;
        Log.d(TAG, "ProfileViewModel: View Model is ready");

    }
    public LiveData<LoginResource<ProfileDetails>> getAuthenticatedUser(){

        return sessionManager.getAuthUser();
    }


    public LiveData<Resource<ActualiteDetails>> queryAllActualiteByAuthorization(String Authorization){




        return LiveDataReactiveStreams.fromPublisher(ActualiteAPI.getAllActualite(Authorization)
                .onErrorReturn(new Function<Throwable, ActualiteDetails>() {
                    @Override
                    public ActualiteDetails apply(Throwable throwable) throws Exception {

                        ActualiteDetails act = new ActualiteDetails("-1");
                        return act;

                    }
                })

                .map(new Function<ActualiteDetails, Resource<ActualiteDetails>>() {
                    @Override
                    public Resource<ActualiteDetails> apply(ActualiteDetails user) throws Exception {
                        Log.d(TAG, "apply:  "+ user.toString());
                        if(user.posts.get(0).description.equals("-1")){
                            return Resource.error("Could not Get Data",(ActualiteDetails)null);
                        }
                        return Resource.authenticated(user);
                    }
                }).subscribeOn(Schedulers.io()));
    }




}