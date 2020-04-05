package com.example.Fabulous.DI.Main.Formation.MesFormations;

import com.example.Fabulous.UI.Adapters.MesFormationsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MesFormationsModule {

    @Provides
    static MesFormationsAdapter provideAdapter(){
        return new MesFormationsAdapter();
    }

}
