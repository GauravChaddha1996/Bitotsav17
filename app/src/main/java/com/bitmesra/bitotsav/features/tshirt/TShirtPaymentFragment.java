package com.bitmesra.bitotsav.features.tshirt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;
import com.bitmesra.bitotsav.ui.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class TShirtPaymentFragment extends BaseFragment {
    String[] ITEMS = {"S", "M", "L", "XL", "XXL"};
    @BindView(R.id.teeitid)
    EditText teeId;
    @BindView(R.id.teeid_til)
    TextInputLayout teeidTil;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.email_til)
    TextInputLayout emailTil;
    @BindView(R.id.tshirt_dropdown)
    MaterialSpinner tshirtDropdown;
    @BindView(R.id.payment_button)
    Button paymentButton;
    @BindView(R.id.check_your_details)
    CustomTextView checkYourDetails;
    @BindView(R.id.payinfo_teeid)
    CustomTextView payinfoTeeid;
    @BindView(R.id.payinfo_email)
    CustomTextView payinfoEmail;
    @BindView(R.id.payinfo_name)
    CustomTextView payinfoName;
    @BindView(R.id.payinfo_college)
    CustomTextView payinfoCollege;
    @BindView(R.id.proceed_button)
    Button proceedButton;

    public TShirtPaymentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tshirt_payment, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tshirtDropdown.setAdapter(adapter);
        startListener();
        return view;
    }

    void startListener() {
        teeId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (teeId.getText().toString().trim().isEmpty()) {
                    teeidTil.setError("Enter your TEE id");
                } else {
                    teeidTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    emailTil.setError("Enter correct email address");
                } else {
                    emailTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.PAYTSHIRT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.TSHIRT;
    }

    @OnClick(R.id.payment_button)
    void payment() {
        if (errorCheck()) {
            final Snackbar snackbar = Snackbar.make(teeId, "Fetching your details.Give us a second.", Snackbar.LENGTH_INDEFINITE);
            DataManager.getDataManager().getTeeInfo(getActivity(), teeId.getText().toString(),
                    email.getText().toString()).doOnSubscribe(snackbar::show)
                    .subscribe(paymentResponse -> {
                        snackbar.dismiss();
                        if (!paymentResponse.getError().equals("true")) {
                            checkYourDetails.setVisibility(View.VISIBLE);
                            payinfoTeeid.setVisibility(View.VISIBLE);
                            payinfoEmail.setVisibility(View.VISIBLE);
                            payinfoName.setVisibility(View.VISIBLE);
                            payinfoCollege.setVisibility(View.VISIBLE);
                            proceedButton.setVisibility(View.VISIBLE);
                            payinfoTeeid.setText("TEE Id: " + paymentResponse.getTeeId());
                            payinfoEmail.setText("Email: " + paymentResponse.getEmail());
                            payinfoName.setText("Name: " + paymentResponse.getName());
                            payinfoCollege.setText("College: " + paymentResponse.getCollege());
                        } else {
                            Snackbar.make(email, paymentResponse.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                        }
                    }, throwable -> {
                        throwable.printStackTrace();
                        Snackbar.make(email, "Cannot connect to server", Snackbar.LENGTH_LONG).show();
                    });
        }
    }

    @OnClick(R.id.proceed_button)
    void proceed() {
        final Snackbar snackbar = Snackbar.make(email, "Starting your payment. Give us a second", Snackbar.LENGTH_INDEFINITE);
        DataManager.getDataManager().getTeePaymentUrl(getActivity(), teeId.getText().toString(),
                email.getText().toString(), ITEMS[tshirtDropdown.getSelectedItemPosition() - 1])
                .doOnSubscribe(snackbar::show)
                .subscribe(paymentResponse -> {
                    snackbar.dismiss();
                    if (!paymentResponse.getError().equals("true")) {
                        ((MainActivity) getActivity()).url = paymentResponse.getUrl();
                        ((MainActivity) getActivity()).setFragment(IdForFragment.TEEWEBVIEW);
                    } else {
                        Snackbar.make(email, paymentResponse.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    Snackbar.make(email, "Cannot connect to server", Snackbar.LENGTH_LONG).show();
                });
    }

    boolean errorCheck() {
        boolean toReturn = true;
        if (teeId.getText().toString().trim().isEmpty()) {
            toReturn = false;
            teeidTil.setError("Enter your TEE id");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            toReturn = false;
            emailTil.setError("Enter correct email address");
        }
        if (tshirtDropdown.getSelectedItemPosition() == 0) {
            toReturn = false;
            tshirtDropdown.setError("Please select a size");
        }
        return toReturn;
    }
}
