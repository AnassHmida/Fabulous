package com.example.Fabulous.DI.Main.Actualite.NewActualitePost;

import com.example.Fabulous.Network.Actualite.List.ActualiteService;
import com.example.Fabulous.Network.Actualite.New.ActualiteNewPostService;
import com.example.Fabulous.Network.Login.LoginService;

import com.example.Fabulous.UI.Activities.ActualiteActivity.NewActualitePostViewModel;
import com.example.Fabulous.UI.Adapters.ActualitesAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NewActualitePostModule {


    @Provides

    static ActualiteNewPostService provideActualiteNewPostService(Retrofit retrofit){
        return  retrofit.create(ActualiteNewPostService.class);
    }
}
