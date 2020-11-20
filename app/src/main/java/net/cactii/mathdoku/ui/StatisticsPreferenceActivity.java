package net.cactii.mathdoku.ui;

import android.os.Bundle;

import net.cactii.mathdoku.R;

public class StatisticsPreferenceActivity extends AppActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.statistics_settings_actionbar_title);

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,
                        new StatisticsPreferenceFragment()).commit();
    }
}
