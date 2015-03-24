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
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsMPActivity extends ActionBarActivity {

    private static final String LISTE_MP_KEY = "";
    private static final String ARRAY_MP_KEY = "";
    private Button btnPlotMP;
    private ListView listeView;
    private ProgressDialog progressDialog;
    private ProgressDialog dialogP;
    private ClientSQLmetier clientBDD;
    private ArrayList<UsageMP> resRequete = new ArrayList<>();
    private int n=10;

    public StatsMPActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
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
        ;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void printExceptionMessage(Exception e) {

    }


    public void addNewLigneTemp(Temp temp) {

    }

    public class Recuperation extends AsyncTask<Void, Void, Void> {
        ArrayAdapter<UsageDD> liste;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String ip = prefs.getString(PreferencesFragment.PREFKEY_HOSTNAME, "82.233.223.249");
                String port = prefs.getString(PreferencesFragment.PREFKEY_PORT, "1433");
                String username = prefs.getString(PreferencesFragment.PREFKEY_USERNAME, "supervision");
                String password = prefs.getString(PreferencesFragment.PREFKEY_PASSWORD, "Password1234");

                try {
                    clientBDD = new ClientSQLmetier(ip, "Supervision", username, password, port, 10);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ResultSet tab = clientBDD.getTableUsageMP(n);

                while (tab.next()) {
                    resRequete.add(new UsageMP(tab.getString("date"),tab.getInt("nbProcs"), tab.getInt("usageMP1"), tab.getInt("usageMP2"),tab.getInt("usageMP3"),tab.getInt("usageMP4"),tab.getInt("usageMP5"),tab.getInt("usageMP6"),tab.getInt("usageMP7"),tab.getInt("usageMP8")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            liste = new ArrayAdapter(StatsMPActivity.this, android.R.layout.simple_list_item_1, resRequete);
            listeView.setAdapter(liste);
            liste.notifyDataSetChanged();
        }
    }
}
