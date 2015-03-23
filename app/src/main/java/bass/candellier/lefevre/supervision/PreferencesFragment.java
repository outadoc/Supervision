package bass.candellier.lefevre.supervision;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PreferencesFragment extends PreferenceFragment {
    public static final String PREFKEY_HOSTNAME ="PREFKEY_HOSTNAME";
    public static final String PREFKEY_PORT="PREFKEY_PORT";
    public static final String PREFKEY_USERNAME ="PREFKEY_USERNAME";
    public static final String PREFKEY_PASSWORD ="PREFKEY_PASSWORD";


    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
