package bass.candellier.lefevre.supervision;

import android.app.Activity;
import android.os.Bundle;
import com.androidplot.xy.XYPlot;

public class PlotTempActivity extends Activity {

    private static final String LISTE_TEMP_KEY = "";
    private XYPlot plot;

    public PlotTempActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
