package com.bitmesra.bitotsav.features.register;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.UserDetailsDto;
import com.bitmesra.bitotsav.database.models.login.SignUpBody;

/**
 * Created by Batdroid on 6/2/17 for Bitotsav.
 */

public class SignUpPresenter implements SignUpPresenterInterface {
    Context context;
    SignUpViewInterface viewInterface;
    DataManager dataManager;

    public SignUpPresenter(Context context, SignUpViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public void register(SignUpBody body) {
        DataManager.getDataManager().signUp(context, body)
                .subscribe(signUpResultBodyResponse -> {
                    body.setBitId(signUpResultBodyResponse.body().getBitId());
                    dataManager.getRealmManager().saveUserDetails(new UserDetailsDto(body));
                });
    }
}
