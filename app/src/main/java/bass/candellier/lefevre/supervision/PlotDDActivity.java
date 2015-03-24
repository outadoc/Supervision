package bass.candellier.lefevre.supervision;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.Arrays;

public class PlotDDActivity extends ActionBarActivity {

    private static final String LISTE_DD_KEY = "";
    private XYPlot plot;
    private ArrayList<UsageDD> resRequete = new ArrayList<>();
    private Number[] usageX;
    private Number[] usageY;
    private int i = 0;

    public PlotDDActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_dd);

        // On récupère toutes les données.
        resRequete = this.getIntent().getParcelableArrayListExtra("dd");

        // On isole les usages.
        usageY = new Number[resRequete.size()];
        for (i = 0; i < resRequete.size(); i++) {
            usageY[i] = Float.parseFloat(resRequete.get(i).getUsage());
        }

        // On récupère le minimum et le maximum des usages du disque pour centrer la courbe par la suite.
        float min = usageY[0].floatValue();
        float max = usageY[0].floatValue();
        for (i = 0; i < usageY.length; i++) {
            if (usageY[i].floatValue() < min) {
                min = usageY[i].floatValue();
            }
            if (usageY[i].floatValue() > max) {
                max = usageY[i].floatValue();
            }
        }

        // On définit également l'espace horizontale entre chaque point pour arriver jusqu'à 100 (l'échelle des temps est relative).
        usageX = new Number[usageY.length];
        int step = 100 / usageY.length;
        for (i = 0; i < usageY.length; i++) {
            if (i > 0) {
                usageX[i] = step +usageX[i - 1].floatValue();
            } else {
                usageX[i] = step;
            }
        }

        plot = (XYPlot) findViewById(R.id.plot_dd);
        plot.setTitle("Usages du disque du " + resRequete.get(0).getSdate() + " au " + resRequete.get(resRequete.size() - 1).getSdate());
        plot.setRangeLabel("%");
        plot.setDomainLabel("Temps relatif");
        plot.setRangeBoundaries(min - 5, max + 5, BoundaryMode.FIXED);
        plot.getBorderPaint().setColor(Color.BLACK);
        plot.getBackgroundPaint().setColor(Color.BLACK);
        plot.setDrawingCacheBackgroundColor(Color.BLACK);
        plot.getGraphWidget().getBackgroundPaint().setColor(Color.BLACK);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

        // On trace la courbe avec ses coordonnées X et Y, et on lui donne un nom.
        XYSeries courbe_dd = new SimpleXYSeries(Arrays.asList(usageX), Arrays.asList(usageY), "Usage du disque en %");

        // On définit la couleur de la courbe, la couleur des points, le transparent sous la courbe, et la couleur des valeurs de chaque point.
        plot.addSeries(courbe_dd, new LineAndPointFormatter(Color.rgb(255, 0, 0), Color.rgb(0, 0, 0), Color.argb(20, 255, 0, 0), new PointLabelFormatter(Color.BLACK)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plot_temp, menu);
        return true;
    }
}
