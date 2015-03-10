package bass.candellier.lefevre.supervision;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by outadoc on 10/03/15.
 */
public class PreferencesFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
