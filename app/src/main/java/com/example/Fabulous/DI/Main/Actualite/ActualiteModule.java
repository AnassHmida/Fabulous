package com.example.Fabulous.DI.Main.Actualite;

import com.example.Fabulous.Network.Actualite.List.ActualiteService;
import com.example.Fabulous.Network.Login.LoginService;
import com.example.Fabulous.UI.Adapters.ActualitesAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ActualiteModule {

    @Provides
    static ActualitesAdapter provideAdapter(){
        return new ActualitesAdapter();
    }
    @Provides

    static ActualiteService provideLoginService(Retrofit retrofit){
        return  retrofit.create(ActualiteService.class);
    }
}
