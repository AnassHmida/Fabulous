package com.example.Fabulous.Network.Actualite.New;



import io.reactivex.Completable;
import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface ActualiteNewPostService {
    @Multipart
    @POST("api/post/new")
    Flowable<Response<Void>> NewPost(
                                            @Header("Authorization") String Authorization,
                                           @Part MultipartBody.Part image,
                                           @Part("data") RequestBody data);
}
