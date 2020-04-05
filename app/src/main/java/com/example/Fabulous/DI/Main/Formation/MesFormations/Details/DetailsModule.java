package com.example.Fabulous.DI.Main.Formation.MesFormations.Details;

import com.example.Fabulous.UI.Activities.DetailsActivity.DetailsActivity;
import com.example.Fabulous.UI.Adapters.ViewPagerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

    @Provides
    static ViewPagerAdapter provideViewPagerAdapter(DetailsActivity detailsActivity) {
        return new ViewPagerAdapter(detailsActivity.getSupportFragmentManager());
    }

}
