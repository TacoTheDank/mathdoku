package net.cactii.mathdoku.ui;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import net.cactii.mathdoku.Preferences;
import net.cactii.mathdoku.storage.database.DatabaseHelper;
import net.cactii.mathdoku.util.Util;

public class AppFragmentActivity extends AppCompatActivity implements
        OnSharedPreferenceChangeListener {

    // Preferences
    public Preferences mMathDokuPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize global objects (singleton instances)
        mMathDokuPreferences = Preferences.getInstance(this);
        DatabaseHelper.getInstance(this);
        new Util(this);

        // Register listener for changes of the share preferences.
        mMathDokuPreferences.mSharedPreferences
                .registerOnSharedPreferenceChangeListener(this);

        setFullScreenWindowFlag();
        setKeepScreenOnWindowFlag();
    }

    @Override
    protected void onDestroy() {
        if (mMathDokuPreferences != null
                && mMathDokuPreferences.mSharedPreferences != null) {
            mMathDokuPreferences.mSharedPreferences
                    .unregisterOnSharedPreferenceChangeListener(this);
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        setFullScreenWindowFlag();
        setKeepScreenOnWindowFlag();
        super.onResume();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if (key.equals(Preferences.PUZZLE_SETTING_FULL_SCREEN)) {
            setFullScreenWindowFlag();
        }
        if (key.equals(Preferences.PUZZLE_SETTING_WAKE_LOCK)) {
            setKeepScreenOnWindowFlag();
        }
    }

    /**
     * Sets the full screen flag for the window in which the activity is shown
     * based on the app preference.
     */
    private void setFullScreenWindowFlag() {
        // Check whether full screen mode is preferred.
        if (mMathDokuPreferences.isFullScreenEnabled()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * Sets the keep screen on flag for the given window in which the activity
     * is shown based on the app preference.
     */
    private void setKeepScreenOnWindowFlag() {
        if (mMathDokuPreferences.isWakeLockEnabled()) {
            getWindow()
                    .addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}
