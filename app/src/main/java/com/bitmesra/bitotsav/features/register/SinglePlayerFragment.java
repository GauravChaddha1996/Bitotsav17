package com.bitmesra.bitotsav.features.register;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class SinglePlayerFragment extends BaseFragment {


    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.name_til)
    TextInputLayout nameTil;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.email_til)
    TextInputLayout emailTil;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.phone_til)
    TextInputLayout phoneTil;
    @BindView(R.id.college)
    EditText college;
    @BindView(R.id.college_til)
    TextInputLayout collegeTil;
    @BindView(R.id.sap)
    EditText sap;
    @BindView(R.id.sap_til)
    TextInputLayout sapTil;
    @BindView(R.id.register_button)
    Button registerButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_player_register, container, false);
        ButterKnife.bind(this, view);
        createListeners();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.SINGLEPLAYER;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.REGISTER;
    }

    @OnClick(R.id.register_button)
    public void single_register() {
        if (errorChecks()) {
            final Snackbar registring = Snackbar.make(name, "Registring...", Snackbar.LENGTH_INDEFINITE);
            DataManager.getDataManager().register(getActivity(),
                    name.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString(),
                    college.getText().toString(),
                    sap.getText().toString())
                    .doOnSubscribe(() -> {
                        registring.show();
                        registerButton.setEnabled(false);
                    })
                    .subscribe(registrationResponse -> {
                        registerButton.setEnabled(true);
                        registring.dismiss();
                        if (registrationResponse.getError().equals("true")) {
                            Snackbar.make(name, registrationResponse.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                        } else {
                            Snackbar snackbar = Snackbar.make(name, "Registration Complete. BIT id: "
                                    + registrationResponse.getBitId() + " (Copied to Clipboard)", Snackbar.LENGTH_INDEFINITE);
                            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText(registrationResponse.getBitId(),
                                    registrationResponse.getBitId());
                            clipboard.setPrimaryClip(clip);
                            snackbar.setAction("Pay here", v -> ((MainActivity)
                                    getActivity()).setFragment(IdForFragment.PAY));
                            snackbar.show();
                        }
                    }, throwable -> {
                        registerButton.setEnabled(true);
                        throwable.printStackTrace();
                        Snackbar.make(name, "Cannot connect to server (Kicked)", Snackbar.LENGTH_SHORT).show();
                    });
        }
    }

    void createListeners() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (name.getText().toString().trim().isEmpty()) {
                    nameTil.setError("Every Gamer has a nick. Please Enter one.");
                } else {
                    nameTil.setError(null);
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
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (phone.getText().toString().length() < 10) {
                    phoneTil.setError("Enter a correct phone number");
                } else {
                    phoneTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        college.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (college.getText().toString().trim().isEmpty()) {
                    collegeTil.setError("Enter a correct college name");
                } else {
                    collegeTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean errorChecks() {
        boolean toReturn = true;
        if (name.getText().toString().trim().isEmpty()) {
            toReturn = false;
            nameTil.setError("Every Gamer has a nick. Please Enter one.");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            toReturn = false;
            emailTil.setError("Enter a correct email address");
        }
        if (phone.getText().toString().length() < 10) {
            toReturn = false;
            phoneTil.setError("Enter a correct phone number");
        }
        if (college.getText().toString().trim().isEmpty()) {
            toReturn = false;
            collegeTil.setError("Enter a correct college name");
        }
        return toReturn;
    }


}
