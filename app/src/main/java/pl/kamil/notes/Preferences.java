package pl.kamil.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static String PASSWORD_KEY = "PASSWORD";

    public static void setPassword(Context context, String password) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(PASSWORD_KEY, password).apply();
    }

    public static String getPasswordHash(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(PASSWORD_KEY, "");
    }
}
