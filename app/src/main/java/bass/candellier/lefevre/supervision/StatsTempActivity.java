package bass.candellier.lefevre.supervision;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class StatsTempActivity extends Activity {

    private static final String LISTE_TEMP_KEY = "";
    private static final String ARRAY_TEMP_KEY = "";
    private Button btnPlotTemp;
    private ListView listeView;
    private ProgressDialog dialogP;

    public StatsTempActivity() {
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

    public void startThreadLectureTemp(ClientSQLmetier base) {

    }

    public void addNewLigneTemp(Temp temp) {

    }
}
