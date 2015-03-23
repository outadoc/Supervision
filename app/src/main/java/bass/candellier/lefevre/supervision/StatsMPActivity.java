package bass.candellier.lefevre.supervision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

public class StatsMPActivity extends ActionBarActivity {

    private static final String LISTE_MP_KEY = "";
    private static final String ARRAY_MP_KEY = "";
    private Button btnPlotMP;
    private ListView listeView;
    private ProgressDialog progressDialog;
    private ProgressDialog dialogP;
    private ClientSQLmetier clientBDD;

    public StatsMPActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        setContentView(R.layout.activity_stats_mp);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stats_mp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                startActivity(new Intent(this, PlotMPActivity.class)/*.putParcelableArrayListExtra("temp", resRequete)*/);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void printExceptionMessage(Exception e) {

    }

    public void printExceptionMessageOnUIThread(Exception e) {

    }

    public void startThreadLectureUsageMP(ClientSQLmetier base) {

    }

    public void addNewLigneUsageMP(UsageMP temp) {

    }
}
