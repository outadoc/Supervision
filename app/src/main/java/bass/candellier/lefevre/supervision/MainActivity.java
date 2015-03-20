package bass.candellier.lefevre.supervision;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

	public static final int AUTO_REFRESH_PERIOD_MS = 10000;
	public static final int PROGRESS_BAR_INTERVAL_MS = 100;

	private TextView lblTemperatureBaie;
	private TextView lblUtilisationDisque;
	private GridView gridCpuUsage;

	private ProgressBar progressBar;
	private TextView lblProgressStatus;
	private Button buttonListeTemp;

	private CPUUsageAdapter cpuAdapter;
	private String[] cpuUsageList;

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
		gridCpuUsage = (GridView) findViewById(R.id.container_cpu_usage);

		progressBar = (ProgressBar) findViewById(R.id.progress_status);
		lblProgressStatus = (TextView) findViewById(R.id.lbl_progress_status);

		cpuUsageList = new String[SnmpGetTask.NB_CPU_CORES];
		cpuAdapter = new CPUUsageAdapter(this, cpuUsageList);
		gridCpuUsage.setAdapter(cpuAdapter);
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

		}, 0, AUTO_REFRESH_PERIOD_MS);
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
			case R.id.action_settings:
				startActivity(new Intent(this, SetPreferencesFragmentActivity.class));
				return true;
			case R.id.action_liste_temperature:
				startActivity(new Intent(this, StatsTempActivity.class));
				return true;
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

				lblUtilisationDisque.setText(String.valueOf(Integer.parseInt(vars[1]) / Integer.parseInt(vars[0])) + "%");

				System.arraycopy(vars, 2, cpuUsageList, 0, SnmpGetTask.NB_CPU_CORES);
				cpuAdapter.notifyDataSetChanged();
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

				List<String> hwOidList = new ArrayList<String>();

				hwOidList.add(SnmpGetTask.OID_HDD_USAGE);
				hwOidList.add(SnmpGetTask.OID_HDD_CAPACITY);

				for(int i = 2; i < SnmpGetTask.NB_CPU_CORES + 2; i++) {
					hwOidList.add(SnmpGetTask.OID_BASE_CPU_USAGE + i);
				}

				String[] hwOidArray = new String[hwOidList.size()];
				hwOidList.toArray(hwOidArray);

				hwTask.execute(hwOidArray);

				sondeTask.execute(new String[]{
						SnmpGetTaskSonde.OID_SONDE_TEMP
				});


			}
		});

	}

	public void onRefreshed() {
		progressBar.setIndeterminate(false);
		progressBar.setMax(AUTO_REFRESH_PERIOD_MS / PROGRESS_BAR_INTERVAL_MS);
		progressBar.setProgress(AUTO_REFRESH_PERIOD_MS / PROGRESS_BAR_INTERVAL_MS);

		countDownTimer = new CountDownTimer(AUTO_REFRESH_PERIOD_MS, PROGRESS_BAR_INTERVAL_MS) {

			@Override
			public void onTick(long millisUntilFinished) {
				progressBar.setProgress((int) (millisUntilFinished / PROGRESS_BAR_INTERVAL_MS));
				lblProgressStatus.setText(getString(R.string.refresh_text_countdown,
						(int) Math.ceil(millisUntilFinished / 1000)));
			}

			@Override
			public void onFinish() {
			}

		}.start();
	}

}
