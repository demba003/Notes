package pl.kamil.notes.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String PASSWORD_KEY = "PASSWORD";

    public static void setPassword(Context context, String password) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(PASSWORD_KEY, password).apply();
    }

    public static String getPassword(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(PASSWORD_KEY, "");
    }
}
