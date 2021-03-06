package bass.candellier.lefevre.supervision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsDDActivity extends ActionBarActivity {

	private static final String LISTE_DD_KEY = PlotDDActivity.LISTE_DD_KEY;
	private static final String ARRAY_DD_KEY = "";
	private int n = 10;
	private ListView listeView;
	private ProgressDialog progressDialog;
	private ClientSQLmetier clientBdd;
	private ArrayList<UsageDD> resRequete = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		progressDialog = new ProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		n = Integer.valueOf(prefs.getString("PREFKEY_NB_VALUES", "10"));

		setContentView(R.layout.activity_stats_dd);
		listeView = (ListView) findViewById(R.id.liste_stats_dd);
		new Recuperation().execute();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_stats_dd, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_settings: {
				startActivity(new Intent(this, PlotDDActivity.class).putParcelableArrayListExtra(LISTE_DD_KEY, resRequete));
				return true;
			}
		}

		return super.onOptionsItemSelected(item);
	}

	public class Recuperation extends AsyncTask<Void, Void, Void> {
		ArrayAdapter<UsageDD> liste;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				String ip = prefs.getString(PreferencesFragment.PREFKEY_HOSTNAME, "82.233.223.249");
				String port = prefs.getString(PreferencesFragment.PREFKEY_PORT, "1433");
				String username = prefs.getString(PreferencesFragment.PREFKEY_USERNAME, "supervision");
				String password = prefs.getString(PreferencesFragment.PREFKEY_PASSWORD, "Password1234");

				try {
					clientBdd = new ClientSQLmetier(ip, "Supervision", username, password, port, 10);
				} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}

				ResultSet tab = clientBdd.getTableUsageDD(n);

				while(tab.next()) {
					resRequete.add(new UsageDD(tab.getString("date"), tab.getString("usage"), tab.getLong("capacité"),
							tab.getLong("utilisé")));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage("Récupération des utilisations du disque en cours");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			if(progressDialog.isShowing()) {
				progressDialog.dismiss();
			}

			liste = new ArrayUsageDDAdapter(StatsDDActivity.this, R.layout.dd_list, resRequete);
			listeView.setAdapter(liste);
			liste.notifyDataSetChanged();
		}
	}
}
