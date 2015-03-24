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

public class PlotTempActivity extends ActionBarActivity {

    private static final String LISTE_TEMP_KEY = "";
    private XYPlot plot;
    private ArrayList<Temp> resRequete = new ArrayList<>();
    private Number[] temperaturesX;
    private Number[] temperaturesY;
    private int i = 0;

    public PlotTempActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_temp);

        // On récupère toutes les données.
        resRequete = this.getIntent().getParcelableArrayListExtra("temp");

        // On isole les températures.
        temperaturesY = new Number[resRequete.size()];
        for (i = 0; i < resRequete.size(); i++) {
            temperaturesY[i] = Float.parseFloat(resRequete.get(i).getTemp());
        }

        // On récupère le minimum et le maximum des températures pour centrer la courbe par la suite.
        float min = temperaturesY[0].floatValue();
        float max = temperaturesY[0].floatValue();
        for (i = 0; i < temperaturesY.length; i++) {
            if (temperaturesY[i].floatValue() < min) {
                min = temperaturesY[i].floatValue();
            }
            if (temperaturesY[i].floatValue() > max) {
                max = temperaturesY[i].floatValue();
            }
        }

        // On définit également l'espace horizontale entre chaque point pour arriver jusqu'à 100 (l'échelle des temps est relative).
        temperaturesX = new Number[temperaturesY.length];
        int step = 100 / temperaturesY.length;
        for (i = 0; i < temperaturesY.length; i++) {
            if (i > 0) {
                temperaturesX[i] = step + temperaturesX[i - 1].floatValue();
            } else {
                temperaturesX[i] = step;
            }
        }

        // Divers modifications visuelles du graphique, après son instanciation.
        plot = (XYPlot) findViewById(R.id.plot_temp);
        plot.setTitle("Températures du " + resRequete.get(0).getSdate() + " au " + resRequete.get(resRequete.size() - 1).getSdate());
        plot.setRangeLabel("°C");
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
        XYSeries courbe_temp = new SimpleXYSeries(Arrays.asList(temperaturesX), Arrays.asList(temperaturesY), "Température de la baie en °C");

        // On définit la couleur de la courbe, la couleur des points, le transparent sous la courbe, et la couleur des valeurs de chaque point.
        plot.addSeries(courbe_temp, new LineAndPointFormatter(Color.rgb(255, 0, 0), Color.rgb(0, 0, 0), Color.argb(20, 255, 0, 0), new PointLabelFormatter(Color.BLACK)));
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