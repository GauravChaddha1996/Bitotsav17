package com.bitmesra.bitotsav.features.tshirt;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.UserDetailsDto;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.register.SignUpActivity;

public class TShirtFragment extends BaseFragment {


    public TShirtFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserDetailsDto detailsDto = DataManager.getDataManager().getRealmManager().getUserDetails();
        if (detailsDto == null) {
            Toast.makeText(getActivity(), "Please sign up before registering for a t-shirt", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), SignUpActivity.class));
        } else {
            Log.d("tga", detailsDto.toString());
            Toast.makeText(getActivity(), detailsDto.getFirstName() + " has registred for a t-shirt!", Toast.LENGTH_SHORT).show();
        }
        return inflater.inflate(R.layout.fragment_tshirt, container, false);
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TSHIRT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }
}
