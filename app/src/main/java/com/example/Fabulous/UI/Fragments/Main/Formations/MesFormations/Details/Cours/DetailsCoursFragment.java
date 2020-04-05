package com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Cours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import com.example.Fabulous.R;
import com.example.Fabulous.Utils.util;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class DetailsCoursFragment extends DaggerFragment {
    private String PDF;

    public DetailsCoursFragment(String PDF) {
        this.PDF = PDF;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_cours_layout, parent, false);
        ButterKnife.bind(this,view);

        WebView webview = (WebView)view.findViewById(R.id.webview);
        ProgressBar progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        webview.getSettings().setJavaScriptEnabled(true);
        String filename =util.imagebaseURL+PDF;
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);
            }
        });
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}