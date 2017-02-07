package com.bitmesra.bitotsav.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.bitmesra.bitotsav.features.EventDtoType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public class Utils {
    private static final Map<String, Typeface> sTypefaceCache = new HashMap<>();

    public static int findEventDtoDayType(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return EventDtoType.TYPE_DAY1;
            case 2:
                return EventDtoType.TYPE_DAY2;
            case 3:
                return EventDtoType.TYPE_DAY3;
            case 4:
                return EventDtoType.TYPE_DAY4;
            default:
                return EventDtoType.TYPE_DAY1;
        }
    }

    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public static
    @CheckResult
    @ColorInt
    int modifyAlpha(@ColorInt int color,
                    @IntRange(from = 0, to = 255) int alpha) {
        return (color & 0x00ffffff) | (alpha << 24);
    }

    public static boolean isNavBarOnBottom(@NonNull Context context) {
        final Resources res = context.getResources();
        final Configuration cfg = context.getResources().getConfiguration();
        final DisplayMetrics dm = res.getDisplayMetrics();
        boolean canMove = (dm.widthPixels != dm.heightPixels &&
                cfg.smallestScreenWidthDp < 600);
        return (!canMove || dm.widthPixels < dm.heightPixels);
    }


    /**
     * Recursive binary search to find the best size for the text.
     * <p>
     * Adapted from https://github.com/grantland/android-autofittextview
     */
    public static float getSingleLineTextSize(String text,
                                              TextPaint paint,
                                              float targetWidth,
                                              float low,
                                              float high,
                                              float precision,
                                              DisplayMetrics metrics) {
        final float mid = (low + high) / 2.0f;

        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mid, metrics));
        final float maxLineWidth = paint.measureText(text);

        if ((high - low) < precision) {
            return low;
        } else if (maxLineWidth > targetWidth) {
            return getSingleLineTextSize(text, paint, targetWidth, low, mid, precision, metrics);
        } else if (maxLineWidth < targetWidth) {
            return getSingleLineTextSize(text, paint, targetWidth, mid, high, precision, metrics);
        } else {
            return mid;
        }
    }


    public static Typeface get(Context context, String font) {
        synchronized (sTypefaceCache) {
            if (!sTypefaceCache.containsKey(font)) {
                Typeface tf = Typeface.createFromAsset(
                        context.getApplicationContext().getAssets(), "fonts/" + font + ".ttf");
                sTypefaceCache.put(font, tf);
            }
            return sTypefaceCache.get(font);
        }
    }
}
