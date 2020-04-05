package com.example.Fabulous.Network.Login;


import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.Models.User;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("api/user/auth")
    Flowable<ProfileDetails> getLoginResponse(@Body User loginInformation);


}
