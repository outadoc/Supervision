package bass.candellier.lefevre.supervision;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

	public static final int AUTO_REFRESH_PERIOD_MS = 15000;
	public static final int PROGRESS_BAR_INTERVAL_MS = 100;

	private TextView lblTemperatureBaie;
	private TextView lblUtilisationDisque;

	private ProgressBar progressBar;
	private TextView lblProgressStatus;

	private CPUUsageAdapter cpuAdapter;
	private String[] cpuUsageList;

	private SnmpGetTask hwTask;
	private SnmpGetTaskSonde sondeTask;

	private Timer refreshTimer;
	private CountDownTimer countDownTimer;

	private boolean hasBeenNotifiedOfRefresh = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblTemperatureBaie = (TextView) findViewById(R.id.lbl_temp_baie);
		lblUtilisationDisque = (TextView) findViewById(R.id.lbl_util_disques);

		progressBar = (ProgressBar) findViewById(R.id.progress_status);
		lblProgressStatus = (TextView) findViewById(R.id.lbl_progress_status);

		cpuUsageList = new String[SnmpGetTask.NB_CPU_CORES];
		cpuAdapter = new CPUUsageAdapter(this, cpuUsageList);

		GridView gridCpuUsage = (GridView) findViewById(R.id.container_cpu_usage);
		gridCpuUsage.setAdapter(cpuAdapter);

		CardView cardTemp = (CardView) findViewById(R.id.card_temp);
		CardView cardDisque = (CardView) findViewById(R.id.card_disque);
		CardView cardCPU = (CardView) findViewById(R.id.card_cpu);

		cardTemp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, StatsTempActivity.class));
			}
		});
		cardDisque.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, StatsDDActivity.class));
			}
		});
		cardCPU.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, StatsMPActivity.class));
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		refreshTimer.cancel();
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
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString("bundle_temp", lblTemperatureBaie.getText().toString());
		outState.putString("bundle_disk", lblUtilisationDisque.getText().toString());
		outState.putStringArray("bundle_cpu_usage", cpuUsageList);
	}

	@Override
	protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		if(lblTemperatureBaie != null) {
			lblTemperatureBaie.setText(savedInstanceState.getString("bundle_temp"));
		}

		if(lblUtilisationDisque != null) {
			lblUtilisationDisque.setText(savedInstanceState.getString("bundle_disk"));
		}

		String[] usages = savedInstanceState.getStringArray("bundle_cpu_usage");
		System.arraycopy(usages, 0, cpuUsageList, 0, SnmpGetTask.NB_CPU_CORES);

		if(cpuAdapter != null) {
			cpuAdapter.notifyDataSetChanged();
		}
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
		}

		return super.onOptionsItemSelected(item);
	}

	public void refreshInterface() {
		hwTask = new SnmpGetTask(this, new SnmpTaskListener() {
			@Override
			public void onResult(String[] vars) {
				if(vars == null) {
					Toast.makeText(MainActivity.this, getString(R.string.error_snmp_normal), Toast.LENGTH_LONG).show();
					onRefreshed();
					return;
				}

				lblUtilisationDisque.setText(String.valueOf(Integer.parseInt(vars[1]) / Integer.parseInt(vars[0])) + "%");

				System.arraycopy(vars, 2, cpuUsageList, 0, SnmpGetTask.NB_CPU_CORES);
				cpuAdapter.notifyDataSetChanged();
				onRefreshed();
			}
		});

		sondeTask = new SnmpGetTaskSonde(this, new SnmpTaskListener() {
			@Override
			public void onResult(String[] vars) {
				if(vars == null) {
					Toast.makeText(MainActivity.this, getString(R.string.error_snmp_sonde),
							Toast.LENGTH_LONG).show();
					onRefreshed();
					return;
				}

				lblTemperatureBaie.setText(vars[0] + "°C");
				onRefreshed();
			}
		});

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// On annule le compte à rebours de rafraîchissement
				if(countDownTimer != null) {
					countDownTimer.cancel();
				}

				hasBeenNotifiedOfRefresh = false;

				progressBar.setIndeterminate(true);
				lblProgressStatus.setText(R.string.refresh_text_refreshing);

				// On récupère les OID pour les coeurs du (des) CPU en les copiant vers une liste
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
		// Si on a déjà remis le compteur à zéro, pas besoin de le refaire
		if(hasBeenNotifiedOfRefresh) {
			return;
		}

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

		hasBeenNotifiedOfRefresh = true;
	}

}
