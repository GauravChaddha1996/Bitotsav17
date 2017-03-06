package com.bitmesra.bitotsav.features.tshirt;

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
import android.widget.ArrayAdapter;
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
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class BookTShirtFragment extends BaseFragment {


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
    @BindView(R.id.room)
    EditText room;
    @BindView(R.id.room_til)
    TextInputLayout roomTil;
    @BindView(R.id.tshirt_dropdown)
    MaterialSpinner tshirtDropdown;
    @BindView(R.id.register_button)
    Button registerButton;
    String[] ITEMS = {"S", "M", "L", "XL", "XXL"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_tshirt, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tshirtDropdown.setAdapter(adapter);
        createListeners();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.BOOKTSHIRT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.TSHIRT;
    }

    @OnClick(R.id.register_button)
    public void single_register() {
        if (errorChecks()) {
            final Snackbar registring = Snackbar.make(name, "Registring...", Snackbar.LENGTH_INDEFINITE);
            DataManager.getDataManager().bookTShirt(getActivity(),
                    name.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString(),
                    ITEMS[tshirtDropdown.getSelectedItemPosition() - 1],
                    college.getText().toString(),
                    room.getText().toString())
                    .doOnSubscribe(() -> {
                        registring.show();
                        registerButton.setEnabled(false);
                    })
                    .subscribe(tShirtBookResponse -> {
                        registerButton.setEnabled(true);
                        registring.dismiss();
                        if (tShirtBookResponse.getError().equals("true")) {
                            Snackbar.make(name, tShirtBookResponse.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                        } else {
                            Snackbar snackbar = Snackbar.make(name, "T-Shirt Booked. T Shirt Id: "
                                    + tShirtBookResponse.getTeeId() + " (Copied to Clipboard)", Snackbar.LENGTH_INDEFINITE);
                            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText(tShirtBookResponse.getTeeId(), tShirtBookResponse.getTeeId());
                            clipboard.setPrimaryClip(clip);
                            snackbar.setAction("Pay here", v -> ((MainActivity)
                                    getActivity()).setFragment(IdForFragment.PAYTSHIRT));
                            snackbar.show();
                        }
                    }, throwable -> {
                        registerButton.setEnabled(true);
                        throwable.printStackTrace();
                        Snackbar.make(name, "Cannot connect to server", Snackbar.LENGTH_SHORT).show();
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
        if (tshirtDropdown.getSelectedItemPosition() == 0) {
            toReturn = false;
            tshirtDropdown.setError("Please choose t-shirt size");
        }
        return toReturn;
    }


}
