package bass.candellier.lefevre.supervision;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsTempActivity extends Activity {

    public static final int n=10;
    private static final String LISTE_TEMP_KEY = "";
    private static final String ARRAY_TEMP_KEY = "";
    private Button btnPlotTemp;
    private ListView listeView;
    private ProgressDialog dialogP;
    private ClientSQLmetier clientBdd;

    public StatsTempActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Recuperation().execute();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void printExceptionMessage(Exception e) {

    }

    public void printExceptionMessageOnUIThread(Exception e) {

    }

    public void startThreadLectureTemp(ClientSQLmetier base) {

    }

    public void addNewLigneTemp(Temp temp) {

    }

    public class Recuperation extends AsyncTask<Void,Void,Void> {
        ArrayAdapter<Temp> liste;
        ArrayList<Temp> listView=new ArrayList<Temp>();

        @Override
        protected Void doInBackground(Void... params) {
            try {
                ResultSet tab=clientBdd.getTableTemperatures(n);
                while(tab.next())
                {
                    listView.add(new Temp(tab.getString("sdate"),tab.getString("temp"),tab.getString("nomBaie")));


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            liste=new ArrayAdapter(StatsTempActivity.this,android.R.layout.simple_list_item_1,listView);

            ListView listef= (ListView) findViewById(R.id.liste_stats_temp);
            listef.setAdapter(liste);
            liste.notifyDataSetChanged();

        }
    }


}
