package bass.candellier.lefevre.supervision;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class StatsDDActivity extends Activity {

    private static final String LISTE_DD_KEY = "";
    private static final String ARRAY_DD_KEY = "";
    private Button btnPlotMP;
    private ListView listeView;
    private ProgressDialog dialogP;

    public StatsDDActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
