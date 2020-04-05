package com.example.Fabulous.UI.Activities.ActualiteActivity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;


import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.Models.User;
import com.example.Fabulous.Network.Actualite.New.ActualiteNewPostService;
import com.example.Fabulous.Network.Login.LoginService;
import com.example.Fabulous.R;
import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginResource;
import com.example.Fabulous.UI.Fragments.Resource;
import com.google.gson.Gson;

import javax.inject.Inject;


import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;


public class NewActualitePostViewModel extends ViewModel {

    ActualiteNewPostService actualiteNewPostService;
    private MediatorLiveData<NewActualitePostResource<Response<Void>>> response = new MediatorLiveData<>();
    private static final String TAG = "NewActualitePostViewMod";
    @Inject
    public NewActualitePostViewModel(ActualiteNewPostService actualiteNewPostService){

        this.actualiteNewPostService = actualiteNewPostService;  }
    public void PassingData(String authorisation, MultipartBody.Part body, RequestBody data){
        Log.d(TAG, "AuthenticateWithId: Attempt to login");
        PublishTrigger(AddNewPost(authorisation,body,data));
    }


    private LiveData<NewActualitePostResource<Response<Void>>> AddNewPost(String authorisation, MultipartBody.Part body, RequestBody data){
        return  LiveDataReactiveStreams.fromPublisher(actualiteNewPostService.NewPost(authorisation,body,data)

                .onErrorReturn((Function<Throwable, Response<Void>>) throwable -> null)
                .map(new Function<Response<Void>, NewActualitePostResource<Response<Void>>>() {
                    @Override
                    public NewActualitePostResource<Response<Void>> apply(Response<Void> resp) throws Exception {

                      if(resp !=null ){
                            return NewActualitePostResource.authenticated(resp);
                        }

                        return  NewActualitePostResource.error("Error",(Response<Void>) null);

                    }
                }).subscribeOn(Schedulers.io()));

    }


    private void PublishTrigger(final LiveData<NewActualitePostResource<Response<Void>>> source){
        if(response != null){
            response.setValue(NewActualitePostResource.loading((Response<Void>)null));
            response.addSource(source, new Observer<NewActualitePostResource<Response<Void>>>() {
                @Override
                public void onChanged(NewActualitePostResource<Response<Void>> userProfileResource) {
                    response.setValue(userProfileResource);
                    response.removeSource(source);
                }
            });
        }
    }




    public LiveData<NewActualitePostResource<Response<Void>>> observePublishTrigger(){
        return response;
    }




}
