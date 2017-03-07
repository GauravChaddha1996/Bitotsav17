package com.bitmesra.bitotsav.features.support;

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
import android.widget.Button;
import android.widget.EditText;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Batdroid on 7/3/17 for Bitotsav.
 */

public class SupportFragment extends BaseFragment {

    @BindView(R.id.support_dropdown)
    MaterialSpinner supportDropdown;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.first_name_til)
    TextInputLayout firstNameTil;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.last_name_til)
    TextInputLayout lastNameTil;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.email_til)
    TextInputLayout emailTil;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.phone_til)
    TextInputLayout phoneTil;
    @BindView(R.id.branchbatch)
    EditText branchbatch;
    @BindView(R.id.branchbatch_til)
    TextInputLayout branchbatchTil;
    @BindView(R.id.msg)
    EditText msg;
    @BindView(R.id.msg_til)
    TextInputLayout msgTil;
    @BindView(R.id.donate_button)
    Button donateButton;
    String ITEMS[] = {"2500", "5000", "7500", "10000"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        ButterKnife.bind(this, view);
        createListeners();
        supportDropdown.setBackgroundColor(getResources().getColor(R.color.card_background));
        supportDropdown.setTextColor(getResources().getColor(R.color.card_text));
        supportDropdown.setArrowColor(getResources().getColor(R.color.card_text));
        supportDropdown.setItems("Rs. 2500", "Rs. 5000", "Rs. 7500", "Rs. 10000");
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.SUPPORT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    @OnClick(R.id.donate_button)
    void donate() {
        if (errorChecks()) {
            final Snackbar registring = Snackbar.make(email, "Registring...", Snackbar.LENGTH_INDEFINITE);
            DataManager.getDataManager().donate(getActivity(),
                    email.getText().toString(),
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    ITEMS[supportDropdown.getSelectedIndex()],
                    phone.getText().toString(),
                    branchbatch.getText().toString(),
                    msg.getText().toString())
                    .doOnSubscribe(() -> {
                        registring.show();
                        donateButton.setEnabled(false);
                    })
                    .subscribe(supportResponse -> {
                        donateButton.setEnabled(true);
                        registring.dismiss();
                        if (!supportResponse.getError().equals("true")) {
                            ((MainActivity) getActivity()).url = supportResponse.getUrl();
                            ((MainActivity) getActivity()).setFragment(IdForFragment.SUPPORTWEBVIEW);
                        } else {
                            Snackbar.make(email, supportResponse.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                        }
                    }, throwable -> {
                        donateButton.setEnabled(true);
                        throwable.printStackTrace();
                        Snackbar.make(email, "Cannot connect to server", Snackbar.LENGTH_LONG).show();
                    });
        }
    }

    void createListeners() {
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (firstName.getText().toString().trim().isEmpty()) {
                    firstNameTil.setError("Please enter your first name");
                } else {
                    firstNameTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (lastName.getText().toString().trim().isEmpty()) {
                    lastNameTil.setError("Please enter your last name");
                } else {
                    lastNameTil.setError(null);
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
                    emailTil.setError("Enter a correct email address");
                } else {
                    emailTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean errorChecks() {
        boolean toReturn = true;
        if (firstName.getText().toString().trim().isEmpty()) {
            toReturn = false;
            firstNameTil.setError("Please enter your first name");
        }
        if (lastName.getText().toString().trim().isEmpty()) {
            toReturn = false;
            lastNameTil.setError("Please enter your first name");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            toReturn = false;
            emailTil.setError("Enter a correct email address");
        }
        return toReturn;
    }
}
