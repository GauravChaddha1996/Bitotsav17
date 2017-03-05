package com.bitmesra.bitotsav.features.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class WebviewFragment extends BaseFragment {
    @BindView(R.id.payment_webview)
    WebView webView;

    public WebviewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, view);
        setUpWebView();
        return view;
    }

    void setUpWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("tag", url);
                if (url.contains("pay")) {
                    ((MainActivity) getActivity()).setFragment(IdForFragment.HOME);
                    return true;
                }
                return false;
            }
        });
        webView.loadUrl("http://2017.beta.bitotsav.in/register/");
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.WEBVIEW;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.PAY;
    }
}
