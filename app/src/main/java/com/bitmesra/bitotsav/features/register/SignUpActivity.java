package com.bitmesra.bitotsav.features.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.login.SignUpBody;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface {

    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.bit_id)
    EditText bitId;
    @BindView(R.id.dob)
    EditText dob;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.phone_number)
    EditText phoneNumber;

    SignUpPresenter presenter;
    @BindView(R.id.clicktologin)
    TextView clickToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new SignUpPresenter(this, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void register(View view) {
        SignUpBody body = new SignUpBody();
        body.setFirstName(firstName.getText().toString())
                .setLastName(lastName.getText().toString())
                .setBitId(bitId.getText().toString())
                .setdOB(dob.getText().toString())
                .setEmail(email.getText().toString())
                .setPhone(phoneNumber.getText().toString());
        presenter.register(body);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showError() {

    }
}
