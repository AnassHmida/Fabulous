package com.example.Fabulous.DI.Splash;

import androidx.lifecycle.ViewModel;

import com.example.Fabulous.DI.ViewModelKey;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginViewModel;
import com.example.Fabulous.UI.Activities.SplashScreenActivity.SplashScreenViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract  class SplashScreenModelsModule {




        @IntoMap
        @Binds
        @ViewModelKey(SplashScreenViewModel.class)
        public abstract ViewModel bindSplashViewModel(SplashScreenViewModel viewModel);




}
