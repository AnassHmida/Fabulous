package com.example.Fabulous.Network.Actualite.List;




import com.example.Fabulous.Models.ActualiteDetails;


import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface ActualiteService {

    @GET("api/post/allPosts")
    Flowable<ActualiteDetails> getAllActualite (@Header("Authorization") String Authorization);
}
