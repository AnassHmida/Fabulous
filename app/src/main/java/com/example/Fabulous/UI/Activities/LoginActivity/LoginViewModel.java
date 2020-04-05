package com.example.Fabulous.UI.Activities.LoginActivity;



import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import androidx.lifecycle.ViewModel;

import com.example.Fabulous.Network.Login.LoginService;
import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.Models.User;
import com.example.Fabulous.SessionManager;


import javax.inject.Inject;


import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel  extends ViewModel {
    private final LoginService loginService;
    private static final String TAG = "AuthViewModel";
    private SessionManager sessionManager;


    @Inject
    public LoginViewModel(LoginService loginService,SessionManager sessionManager){
        this.loginService = loginService;
        this.sessionManager = sessionManager;   }

        public void AutoAuthentication(LoginResource<ProfileDetails> source){
            sessionManager.AutoAuthentification(source);
        }
    public void AuthenticateWithUser(User userId){
        Log.d(TAG, "AuthenticateWithId: Attempt to login");
        sessionManager.authenticateWithId(queryUserDetails(userId));

    }

    private LiveData<LoginResource<ProfileDetails>> queryUserDetails(User userId){
        return  LiveDataReactiveStreams.fromPublisher(loginService.getLoginResponse(userId)
                .onErrorReturn(new Function<Throwable, ProfileDetails>() {
                    @Override
                    public ProfileDetails apply(Throwable throwable) throws Exception {
                        ProfileDetails errorUser = new ProfileDetails();
                       errorUser.setProfile((ProfileDetails.Profile)null);
                        return errorUser;

                    }
                })

                .map(new Function<ProfileDetails, LoginResource<ProfileDetails>>() {
                    @Override
                    public LoginResource<ProfileDetails> apply(ProfileDetails user) throws Exception {
                        if(user.getProfile() ==null){
                            return LoginResource.error("Could not Authenticate",(ProfileDetails) null);
                        }
                        return LoginResource.authenticated(user);
                    }
                }).subscribeOn(Schedulers.io()));
    }
    public LiveData<LoginResource<ProfileDetails>> observeAuthState(){
        return sessionManager.getAuthUser();
    }
}
