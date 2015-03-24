package bass.candellier.lefevre.supervision;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.XYPlot;

public class PlotMPActivity extends ActionBarActivity {

    private static final String LISTE_MP_KEY = "";
    private XYPlot plot;

    public PlotMPActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_mp);

        plot = (XYPlot) findViewById(R.id.plot_mp);
        //plot.setTitle("Températures du " + resRequete.get(0).getSdate() + " au " + resRequete.get(resRequete.size() - 1).getSdate());
        plot.setRangeLabel("%");
        plot.setDomainLabel("Temps relatif");
        //plot.setRangeBoundaries(min - 5, max + 5, BoundaryMode.FIXED);
        plot.getBorderPaint().setColor(Color.BLACK);
        plot.getBackgroundPaint().setColor(Color.BLACK);
        plot.setDrawingCacheBackgroundColor(Color.BLACK);
        plot.getGraphWidget().getBackgroundPaint().setColor(Color.BLACK);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plot_mp, menu);
        return true;
    }
}
