package net.cactii.mathdoku.ui;

import android.os.Bundle;

import net.cactii.mathdoku.R;

public class ArchivePreferenceActivity extends AppActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.archive_settings_action_bar_title);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new ArchivePreferenceFragment())
                .commit();
    }
}
