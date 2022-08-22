package com.seyma.chatboot.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public class Utils {
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cn.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }

    public static void hideSoftKeyBoard(Context context, View focusView) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
    }

    public static String getDateFromYearMonthDay(int year, int month, int dayOfMonth) {
        StringBuilder result = new StringBuilder();
        if (dayOfMonth < 9) {
            result.append(0).append(dayOfMonth);
        } else {
            result.append(dayOfMonth);
        }
        result.append("/");
        if (month < 9) {
            result.append(0).append(month);
        } else {
            result.append(month);
        }
        result.append("/");
        result.append(year);
        return result.toString();
    }

    public static String toBoth(String text) {
        return "<b>" + text + "</b>";
    }

}
