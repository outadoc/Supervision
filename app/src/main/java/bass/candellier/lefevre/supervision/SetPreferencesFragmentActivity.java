package bass.candellier.lefevre.supervision;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class SetPreferencesFragmentActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getFragmentManager().beginTransaction()
				.replace(R.id.content, new PreferencesFragment())
				.commit();
	}
}
