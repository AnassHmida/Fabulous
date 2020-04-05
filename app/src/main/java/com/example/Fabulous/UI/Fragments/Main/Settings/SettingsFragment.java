package com.example.Fabulous.UI.Fragments.Main.Settings;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Fabulous.R;
import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Fragments.Main.Settings.DisconnectionDialog.DisconnectDialogFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;


public class SettingsFragment extends DaggerFragment {
    @Inject
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, parent, false);
        ButterKnife.bind(this,view);

        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    @OnClick(R.id.disconnect)
    public void Disconnect(){

        DisconnectDialogFragment addPhotoBottomDialogFragment =
                DisconnectDialogFragment.newInstance();
        addPhotoBottomDialogFragment.setSessionManager(sessionManager);
        addPhotoBottomDialogFragment.show(getFragmentManager(),
                "add_photo_dialog_fragment");
    }

}