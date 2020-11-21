package net.cactii.mathdoku.ui;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import net.cactii.mathdoku.Preferences;
import net.cactii.mathdoku.R;

public class StatisticsPreferenceFragment extends PreferenceFragmentCompat implements
        OnSharedPreferenceChangeListener {

    SharedPreferences mSharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.statistics_preferences);

        setMaximumGamesElapsedTimeChart();
    }

    @Override
    public void onStart() {
        mSharedPreferences = Preferences.getInstance(getActivity()).mSharedPreferences;
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);

        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if (key.equals(Preferences.STATISTICS_SETTING_ELAPSED_TIME_CHART_MAXIMUM_GAMES)) {
            setMaximumGamesElapsedTimeChart();
        }
    }

    /**
     * Set summary for option "maximum games elapsed time chart" based on
     * current value of the option.
     */
    public void setMaximumGamesElapsedTimeChart() {
        findPreference(
                Preferences.STATISTICS_SETTING_ELAPSED_TIME_CHART_MAXIMUM_GAMES)
                .setSummary(
                        getResources()
                                .getString(
                                        R.string.statistics_setting_elapsed_time_chart_maximum_games_summary,
                                        Preferences
                                                .getInstance()
                                                .getStatisticsSettingElapsedTimeChartMaximumGames()));
    }
}
