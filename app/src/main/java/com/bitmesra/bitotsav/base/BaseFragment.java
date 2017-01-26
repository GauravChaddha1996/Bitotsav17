package com.bitmesra.bitotsav.base;

import android.support.v4.app.Fragment;

import com.bitmesra.bitotsav.features.IdForFragment;

/**
 * Created by Batdroid on 23/1/17 for Bitotsav.
 */

public abstract class BaseFragment extends Fragment {
    /*Every fragment should do these three things -> Tell what it's enum id is, tell if back is pressed
    * who the activity should go to, and what should be the animation*/
    // TODO: 26/1/17 think of a architecture way to implement animations between fragments
    abstract public IdForFragment getFragmentId();
    abstract public IdForFragment getBackToClazz();
}
