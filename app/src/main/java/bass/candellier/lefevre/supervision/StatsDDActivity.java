package bass.candellier.lefevre.supervision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

public class StatsDDActivity extends ActionBarActivity {

    private static final String LISTE_DD_KEY = "";
    private static final String ARRAY_DD_KEY = "";
    private Button btnPlotMP;
    private ListView listeView;
    private ProgressDialog progressDialog;
    private ProgressDialog dialogP;
    private ClientSQLmetier clientBDD;

    public StatsDDActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        setContentView(R.layout.activity_stats_dd);
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
        switch (item.getItemId()) {
            case R.id.action_settings: {
                startActivity(new Intent(this, PlotDDActivity.class)/*.putParcelableArrayListExtra("temp", resRequete)*/);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void printExceptionMessage(Exception e) {

    }

    public void printExceptionMessageOnUIThread(Exception e) {

    }

    public void startThreadLectureUsageDD(ClientSQLmetier base) {

    }

    public void addNewLigneUsageDD(UsageDD temp) {

    }
}
