package com.example.Fabulous.DI.Login;

import androidx.lifecycle.ViewModel;


import com.example.Fabulous.UI.Activities.LoginActivity.LoginViewModel;
import com.example.Fabulous.DI.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract  class LoginViewModelsModule {

    @IntoMap
    @Binds
    @ViewModelKey(LoginViewModel.class)
    public abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);


}
