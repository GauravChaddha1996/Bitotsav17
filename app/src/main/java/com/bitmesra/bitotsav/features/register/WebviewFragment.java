package com.bitmesra.bitotsav.features.register;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;
import com.bitmesra.bitotsav.ui.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class WebviewFragment extends BaseFragment {
    @BindView(R.id.payment_webview)
    WebView webView;
    @BindView(R.id.payment_progressBarHolder)
    RelativeLayout paymentProgressBarHolder;
    @BindView(R.id.payment_status_text)
    CustomTextView paymentStatusText;
    @BindView(R.id.payment_status_amount)
    CustomTextView paymentStatusAmount;
    @BindView(R.id.payment_status)
    LinearLayout paymentStatus;
    @BindView(R.id.payment_status_image)
    ImageView paymentStatusImage;

    public WebviewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, view);
//        setUpWebView();
//  	  set the payment method here in the function as you like
        return view;
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
