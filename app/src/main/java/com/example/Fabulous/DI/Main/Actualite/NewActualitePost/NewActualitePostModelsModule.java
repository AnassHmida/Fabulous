package com.example.Fabulous.DI.Main.Actualite.NewActualitePost;

import androidx.lifecycle.ViewModel;

import com.example.Fabulous.DI.ViewModelKey;
import com.example.Fabulous.UI.Activities.ActualiteActivity.NewActualitePostViewModel;
import com.example.Fabulous.UI.Fragments.Main.Actualites.List.ActualiteViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


    @Module
    public abstract class NewActualitePostModelsModule {


        @IntoMap
        @Binds
        @ViewModelKey(NewActualitePostViewModel.class)
        public abstract ViewModel bindNewActualitePostViewModel(NewActualitePostViewModel viewModel);




    }


