package com.example.Fabulous.DI.Main.Actualite;

import androidx.lifecycle.ViewModel;

import com.example.Fabulous.DI.ViewModelKey;
import com.example.Fabulous.UI.Fragments.Main.Actualites.List.ActualiteViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ActualiteViewModelsModule {


        @IntoMap
        @Binds
        @ViewModelKey(ActualiteViewModel.class)
        public abstract ViewModel bindActualiteViewModel(ActualiteViewModel viewModel);




}
