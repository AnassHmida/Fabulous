package com.example.Fabulous.UI.Fragments.Main.Settings.DisconnectionDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.Fabulous.R;
import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginActivity;
import com.example.Fabulous.Utils.Alarms.RefreshUserTokenID;
import com.example.Fabulous.Utils.util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

import static com.example.Fabulous.Utils.util.mypreference;

public class DisconnectDialogFragment extends BottomSheetDialogFragment {


     SessionManager sessionManager;

    public static DisconnectDialogFragment newInstance() {
        return new DisconnectDialogFragment();
    }

    public void setSessionManager(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.connection_bottom_sheet, container, false);
        ButterKnife.bind(this,view);



        return view;

    }
    @OnClick(R.id.confirm_disconnect)
    public void Confirm(){
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(mypreference, 0); // 0 - for private mode
        Paper.book().delete(util.bookkey);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(util.key, util.notlogged);
        editor.apply();
        sessionManager.logout();
/*        RefreshUserTokenID refreshUserTokenID = new RefreshUserTokenID();
        refreshUserTokenID.cancelAlarm(getActivity());*/
        Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(i);


    }
    @OnClick(R.id.cancel_disconnect)
    public void Cancel(){
        dismiss();


    }
}