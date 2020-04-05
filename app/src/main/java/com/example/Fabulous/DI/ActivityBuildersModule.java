package com.example.Fabulous.DI;

import com.example.Fabulous.DI.Main.Actualite.ActualiteModule;
import com.example.Fabulous.DI.Main.Actualite.ActualiteViewModelsModule;
import com.example.Fabulous.DI.Main.Actualite.NewActualitePost.NewActualitePostModelsModule;
import com.example.Fabulous.DI.Main.Actualite.NewActualitePost.NewActualitePostModule;
import com.example.Fabulous.DI.Main.Formation.MesFormations.Details.DetailsModule;

import com.example.Fabulous.DI.Main.MainFragmentBuildersModule;
import com.example.Fabulous.DI.Main.MainViewModelsModule;
import com.example.Fabulous.DI.Splash.SplashScreenModelsModule;
import com.example.Fabulous.UI.Activities.ActualiteActivity.NewActualitePostActivity;
import com.example.Fabulous.UI.Activities.DetailsActivity.DetailsActivity;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginActivity;
import com.example.Fabulous.DI.Login.LoginModule;
import com.example.Fabulous.DI.Login.LoginViewModelsModule;
import com.example.Fabulous.UI.Activities.MainActivity.MainActivity;
import com.example.Fabulous.UI.Activities.SplashScreenActivity.SplashScreenActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


@ContributesAndroidInjector(modules = {LoginViewModelsModule.class, LoginModule.class,})
    abstract LoginActivity contributeLoginActivity();


    @ContributesAndroidInjector(modules = {
            MainFragmentBuildersModule.class, MainViewModelsModule.class,ActualiteViewModelsModule.class, ActualiteModule.class,
    })
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {
          SplashScreenModelsModule.class,
    })
    abstract SplashScreenActivity contributeSplashScreenActivity();

    @ContributesAndroidInjector(modules = {
            NewActualitePostModelsModule.class, NewActualitePostModule.class,
    })
    abstract NewActualitePostActivity contributeNewActualitePostActivity();

    @ContributesAndroidInjector(modules = {
            DetailsModule.class,
    })
    abstract DetailsActivity contributeDetailsActivity();





}
