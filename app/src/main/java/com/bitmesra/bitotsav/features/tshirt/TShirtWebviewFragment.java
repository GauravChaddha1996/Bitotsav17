package com.bitmesra.bitotsav.features.tshirt;

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

public class TShirtWebviewFragment extends BaseFragment {
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

    public TShirtWebviewFragment() {
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
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("/response/success")) {
                    String amount = request.getUrl().getQueryParameter("amount");
                    paymentStatus.setVisibility(View.VISIBLE);
                    paymentStatusImage.setImageDrawable(getResources().getDrawable(R.drawable.success));

                    paymentStatusText.setText("Payment Successful");
                    paymentStatusAmount.setText("Amount: " + amount);
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            ((MainActivity) getActivity()).setFragment(IdForFragment.HOME);
                        }
                    };
                    handler.sendEmptyMessageDelayed(0, 2000);
                    return true;
                } else if (request.getUrl().toString().contains("/response/failed")) {
                    paymentStatus.setVisibility(View.VISIBLE);
                    paymentStatusImage.setImageDrawable(getResources().getDrawable(R.drawable.failed));
                    paymentStatusAmount.setVisibility(View.GONE);
                    paymentStatusText.setText("Payment Failed");
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            ((MainActivity) getActivity()).setFragment(IdForFragment.PAYTSHIRT);
                        }
                    };
                    handler.sendEmptyMessageDelayed(0, 2000);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                paymentProgressBarHolder.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                paymentProgressBarHolder.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(((MainActivity) getActivity()).url);
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TEEWEBVIEW;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.PAYTSHIRT;
    }
}
