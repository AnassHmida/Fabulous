package com.example.Fabulous.DI.Main;


import com.example.Fabulous.DI.Main.Formation.FormationsModule;
import com.example.Fabulous.DI.Main.Formation.MesFormations.Details.Video.VideoModule;
import com.example.Fabulous.DI.Main.Formation.MesFormations.MesFormationsModule;
import com.example.Fabulous.DI.Main.Formation.MesFormations.MesFormationsViewModelsModule;
import com.example.Fabulous.UI.Fragments.Main.Actualites.List.ActualiteFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.Formation.FormationFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.FormationsFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Cours.DetailsCoursFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Description.DetailsDescriptionFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Video.DetailsVideoFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.MesFormationsFragment;
import com.example.Fabulous.UI.Fragments.Main.Settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract FormationFragment contributeFormationFragment();


    @ContributesAndroidInjector
    abstract ActualiteFragment contributeActualiteFragment();

    @ContributesAndroidInjector(modules = {
            FormationsModule.class,
    })
    abstract FormationsFragment contributeFormationsFragment();

    @ContributesAndroidInjector(modules = {
            MesFormationsViewModelsModule.class, MesFormationsModule.class,
    })
    abstract MesFormationsFragment contributeMesFormationsFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector(modules = {
             VideoModule.class,
    })
    abstract DetailsVideoFragment contributeDetailsVideoFragment();

    @ContributesAndroidInjector
    abstract DetailsCoursFragment contributeDetailsCoursFragment();

    @ContributesAndroidInjector
    abstract DetailsDescriptionFragment contributeDetailsDescriptionFragment();




}
