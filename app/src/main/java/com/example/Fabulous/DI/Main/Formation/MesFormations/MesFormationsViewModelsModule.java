package com.example.Fabulous.DI.Main.Formation.MesFormations;

import androidx.lifecycle.ViewModel;

import com.example.Fabulous.DI.ViewModelKey;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.MesFormationsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class MesFormationsViewModelsModule {


        @IntoMap
        @Binds
        @ViewModelKey(MesFormationsViewModel.class)
        public abstract ViewModel bindMesFormationsViewModel(MesFormationsViewModel viewModel);




}
