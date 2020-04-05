package com.example.Fabulous.UI.Activities.MainActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.Fabulous.R;

import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Fragments.Main.Actualites.List.ActualiteFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.FormationsFragment;

import com.example.Fabulous.UI.Fragments.Main.Settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;


public class MainActivity extends DaggerAppCompatActivity {

@BindView(R.id.bottom_navigation)
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    /**
     *The function SetFragment is used to insert a fragment  in the place of the mainFrame  Framelyout in the activity_main.xml file.
     * @param fragment is used to indicate the newly insert fragment
     **/

    private void SetFragment(Fragment fragment){
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.mainFrame, fragment);
    ft.commit();

}

    /**
     * The function init is used to initialize the formations fragment ( the default one ) and then set a the bottom navigation bar items click listeners.
     **/
    private void init(){
    SetFragment(new FormationsFragment());
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.forms:
                    SetFragment(new FormationsFragment());
                    break;
                case R.id.acts:
                    SetFragment(new ActualiteFragment());
                    break;
                case R.id.shop:

//Shop Fragment TODO
                    break;
                case R.id.subjects:
//Subjects Fragment TODO

                    break;
                case R.id.config:
                    SetFragment(new SettingsFragment());
                    break;

            }
            return true;
        }
    });
}
    @Override
    public void onBackPressed() {
// super.onBackPressed();
    }

}
