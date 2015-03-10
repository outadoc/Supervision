package bass.candellier.lefevre.supervision;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class StatsMPActivity extends Activity {

    private static final String LISTE_MP_KEY = "";
    private static final String ARRAY_MP_KEY = "";
    private Button btnPlotMP;
    private ListView listeView;
    private ProgressDialog dialogP;

    public StatsMPActivity() {
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

    public void startThreadLectureUsageMP(ClientSQLmetier base) {

    }

    public void addNewLigneUsageMP(UsageMP temp) {

    }
}
