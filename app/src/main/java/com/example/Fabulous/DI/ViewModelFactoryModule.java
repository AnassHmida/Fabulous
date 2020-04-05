package com.example.Fabulous.DI;

import androidx.lifecycle.ViewModelProvider;


import com.example.Fabulous.ViewModel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract  class ViewModelFactoryModule {

    @Binds
    public  abstract ViewModelProvider.Factory bindViewModelFactory (ViewModelProviderFactory modelProviderFactory);




/*    //SameTHING AS ABOVE -- Use Binds if you're not using anything inside the provides.
    @Provides
    static  ViewModelProvider.Factory viewModelProviderFactory (ViewModelProviderFactory factory){
        return  factory;
    }*/
}
