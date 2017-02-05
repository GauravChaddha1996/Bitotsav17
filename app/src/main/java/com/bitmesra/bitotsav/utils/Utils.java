package com.bitmesra.bitotsav.utils;

import android.content.Context;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public class Utils {
    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
