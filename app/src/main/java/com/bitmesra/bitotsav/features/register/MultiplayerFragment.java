package com.bitmesra.bitotsav.features.register;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.features.IdForFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class MultiplayerFragment extends BaseFragment {


    @BindView(R.id.teamname)
    EditText teamname;
    @BindView(R.id.teamname_til)
    TextInputLayout teamnameTil;
    @BindView(R.id.headbitid)
    EditText headbitid;
    @BindView(R.id.headbitit_til)
    TextInputLayout headbititTil;
    @BindView(R.id.commaid)
    EditText commaid;
    @BindView(R.id.commaid_til)
    TextInputLayout commaidTil;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.emailhead_til)
    TextInputLayout emailheadTil;
    @BindView(R.id.teamphone)
    EditText teamphone;
    @BindView(R.id.teamphone_til)
    TextInputLayout teamphoneTil;
    @BindView(R.id.college)
    EditText college;
    @BindView(R.id.college_til)
    TextInputLayout collegeTil;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.events_dropdown)
    MaterialSpinner eventsDropdown;
    String[] event_name = {"saptak",
            "rhapsody", "mun", "nukkad", "talkies", "streetdance", "bitotsav", "bplan", "dancesaga", "cnc"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_register, container, false);
        ButterKnife.bind(this, view);
        String[] ITEMS = {"Saptak(Eastern BoB) -17th March",
                "Rhapsody(western BoB) -18th March",
                "MUN -18th &19th March", "NUKKAD 18th & 19th March",
                "Talkies 18th March", "Street Dance 19th March", "Mr. And Miss Bitotsav 19th & 20th March",
                "B-PLAN 20th March", "Dance Saga 20th March", "CNC(Gaming)"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventsDropdown.setAdapter(adapter);
        createListeners();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.MULTIPLAYER;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.REGISTER;
    }

    @OnClick(R.id.register_button)
    public void single_register() {
        if (errorChecks()) {
            final Snackbar registring = Snackbar.make(teamname, "Registring...", Snackbar.LENGTH_INDEFINITE);
            DataManager.getDataManager().teamRegister(getActivity(),
                    teamname.getText().toString(),
                    email.getText().toString(),
                    teamphone.getText().toString(),
                    event_name[eventsDropdown.getSelectedItemPosition() - 1]
                    , commaid.getText().toString(),
                    headbitid.getText().toString(),
                    college.getText().toString())
                    .doOnSubscribe(() -> {
                        registring.show();
                        registerButton.setEnabled(false);
                    })
                    .subscribe(registrationResponse -> {
                        registerButton.setEnabled(true);
                        registring.dismiss();
                        if (registrationResponse.getError().equals("true")) {
                            Snackbar.make(teamname, registrationResponse.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                        } else {
                            Snackbar.make(teamname, "Registration Complete. BIT id: "
                                    + registrationResponse.getBitId(), Snackbar.LENGTH_INDEFINITE).show();
                        }
                    }, throwable -> {
                        registerButton.setEnabled(true);
                        throwable.printStackTrace();
                        Snackbar.make(teamname, "Cannot connect to server (Kicked)", Snackbar.LENGTH_SHORT).show();
                    });
        }
    }

    void createListeners() {
        teamname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (teamname.getText().toString().trim().isEmpty()) {
                    teamnameTil.setError("Enter a team name");
                } else {
                    teamnameTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        headbitid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (headbitid.getText().toString().trim().isEmpty()) {
                    headbititTil.setError("Enter team head BIT Id");
                } else {
                    headbititTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        commaid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (commaid.getText().toString().trim().isEmpty()) {
                    commaidTil.setError("Enter comma seperated BIT Id of team members");
                } else {
                    commaidTil.setError(null);
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
                    emailheadTil.setError("Enter correct team head email address");
                } else {
                    emailheadTil.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        teamphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (teamphone.getText().toString().length() < 10) {
                    teamphoneTil.setError("Enter correct team head phone number");
                } else {
                    teamphoneTil.setError(null);
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
        eventsDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eventsDropdown.setError(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                eventsDropdown.setError("Choose an event to participate in");
            }
        });
    }

    public boolean errorChecks() {
        boolean toReturn = true;
        if (teamname.getText().toString().trim().isEmpty()) {
            toReturn = false;
            teamnameTil.setError("Enter a team name");
        }
        if (headbitid.getText().toString().trim().isEmpty()) {
            toReturn = false;
            headbititTil.setError("Enter team head BIT Id");
        }
        if (commaid.getText().toString().trim().isEmpty()) {
            toReturn = false;
            commaidTil.setError("Enter comma seperated BIT Id of team members");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            toReturn = false;
            emailheadTil.setError("Enter correct team head email address");
        }
        if (teamphone.getText().toString().length() < 10) {
            toReturn = false;
            teamphoneTil.setError("Enter correct team head phone number");
        }
        if (college.getText().toString().trim().isEmpty()) {
            toReturn = false;
            collegeTil.setError("Enter a correct college name");
        }
        if (eventsDropdown.getSelectedItemPosition() == 0) {
            toReturn = false;
            eventsDropdown.setError("Choose an event to participate in");
        }
        return toReturn;
    }


}
