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

	private SnmpGetTask hwTask;
	private SnmpGetTaskSonde sondeTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblTemperatureBaie = (TextView) findViewById(R.id.lbl_temp_baie);
		lblUtilisationDisque = (TextView) findViewById(R.id.lbl_util_disques);
		gridCpuUsage = (GridLayout) findViewById(R.id.container_cpu_usage);

		progressBar = (ProgressBar) findViewById(R.id.progress_status);
		lblProgressStatus = (TextView) findViewById(R.id.lbl_progress_status);

		hwTask = new SnmpGetTask(this, new SnmpTaskListener() {
			@Override
			public void onResult(String[] vars) {
				if(vars == null) {
					throw new RuntimeException("SNMP a retourné null");
				}

				int freeDisk = Integer.parseInt(vars[1]) / Integer.parseInt(vars[0]);

				lblUtilisationDisque.setText(String.valueOf(freeDisk) + "%");
			}
		});

		sondeTask = new SnmpGetTaskSonde(this, new SnmpTaskListener() {
			@Override
			public void onResult(String[] vars) {
				if(vars == null) {
					throw new RuntimeException("SNMP a retourné null pour sonde");
				}

				lblTemperatureBaie.setText(vars[0] + "°C");
			}
		});

		refreshInterface();
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

	public void refreshInterface() {
		hwTask.execute(new String[]{
				SnmpGetTask.OID_HDD_USAGE,
				SnmpGetTask.OID_HDD_CAPACITY
		});

		sondeTask.execute(new String[]{
				SnmpGetTaskSonde.OID_SONDE_TEMP
		});
	}

}
