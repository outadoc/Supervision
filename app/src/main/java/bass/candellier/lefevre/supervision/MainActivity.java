package bass.candellier.lefevre.supervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	private TextView lblTemperatureBaie;
	private TextView lblUtilisationDisque;
	private GridLayout gridCpuUsage;

	private ProgressBar progressBar;
	private TextView lblProgressStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblTemperatureBaie = (TextView) findViewById(R.id.lbl_temp_baie);
		lblUtilisationDisque = (TextView) findViewById(R.id.lbl_util_disques);
		gridCpuUsage = (GridLayout) findViewById(R.id.container_cpu_usage);

		progressBar = (ProgressBar) findViewById(R.id.progress_status);
		lblProgressStatus = (TextView) findViewById(R.id.lbl_progress_status);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_settings: {
				startActivity(new Intent(this, SetPreferencesFragmentActivity.class));
				return true;
			}
		}

		return super.onOptionsItemSelected(item);
	}

}
