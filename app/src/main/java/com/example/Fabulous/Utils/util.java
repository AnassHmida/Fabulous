package com.example.Fabulous.Utils;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.Fabulous.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class util {
    static Toast mToast;
    public static String baseURL = "http://www.freedev.tn:3001/";
    public static String imagebaseURL = "http://www.freedev.tn:3001/api/";
    public static final String mypreference = "mypref";
    public static final String key = "account";
    public static final String user = "account_user";
    public static final String password = "account_password";
    public static final String logged = "logged";
    public static final String notlogged = "notlogged";
    public static final String bookkey = "loggedmodel";


    public static void showToast(Context context, String statusMsg){
        if(mToast != null) mToast.cancel();
        mToast = Toast.makeText(context,statusMsg, Toast.LENGTH_SHORT);
        mToast.show();
    }
    public static void showSheet(BottomSheetBehavior sheetBehavior){
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
    public static void HideSheet(BottomSheetBehavior sheetBehavior){
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
    public static Dialog ShowProgress(Context context){
        Dialog progress = new Dialog(context);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setContentView(R.layout.loading_dialog);
        progress.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.show();
        return progress;
    }

}
