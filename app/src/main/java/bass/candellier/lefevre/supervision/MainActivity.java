package bass.candellier.lefevre.supervision;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

	private TextView lblTemperatureBaie;
	private TextView lblUtilisationDisque;
	private GridLayout gridCpuUsage;

	private ProgressBar progressBar;
	private TextView lblProgressStatus;
	private Button buttonListeTemp;

	private SnmpGetTask hwTask;
	private SnmpGetTaskSonde sondeTask;

	private Timer refreshTimer;
	private CountDownTimer countDownTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblTemperatureBaie = (TextView) findViewById(R.id.lbl_temp_baie);
		lblUtilisationDisque = (TextView) findViewById(R.id.lbl_util_disques);
		gridCpuUsage = (GridLayout) findViewById(R.id.container_cpu_usage);

		progressBar = (ProgressBar) findViewById(R.id.progress_status);
		lblProgressStatus = (TextView) findViewById(R.id.lbl_progress_status);

		buttonListeTemp = (Button) findViewById(R.id.btn_liste_temperature);
		buttonListeTemp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Class c = StatsTempActivity.class;
				Intent i = new Intent(MainActivity.this, c);
				startActivityForResult(i, 1);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		refreshTimer = new Timer();
		refreshTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				refreshInterface();
			}

		}, 0, 10000);
	}

	@Override
	protected void onPause() {
		super.onPause();
		refreshTimer.cancel();
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
				onRefreshed();
			}
		});

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(countDownTimer != null) {
					countDownTimer.cancel();
				}

				progressBar.setIndeterminate(true);
				lblProgressStatus.setText(R.string.refresh_text_refreshing);

				hwTask.execute(new String[]{
						SnmpGetTask.OID_HDD_USAGE,
						SnmpGetTask.OID_HDD_CAPACITY
				});

				sondeTask.execute(new String[]{
						SnmpGetTaskSonde.OID_SONDE_TEMP
				});


			}
		});

	}

	public void onRefreshed() {
		progressBar.setIndeterminate(false);
		progressBar.setMax(100);
		progressBar.setProgress(100);

		countDownTimer = new CountDownTimer(10000, 100) {

			@Override
			public void onTick(long millisUntilFinished) {
				progressBar.setProgress((int) (millisUntilFinished / 100));
				lblProgressStatus.setText(getString (R.string.refresh_text_countdown, (int) Math.ceil(millisUntilFinished / 1000)));
			}

			@Override
			public void onFinish() {
			}

		}.start();
	}

}
