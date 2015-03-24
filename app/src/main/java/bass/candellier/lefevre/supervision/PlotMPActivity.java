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

public class PlotMPActivity extends ActionBarActivity {

    private static final String LISTE_MP_KEY = "mp";
    private XYPlot plot;
    private ArrayList<UsageMP> resRequete = new ArrayList<>();
    private Number[] usageMPX;
    private Number[] usageMPY;
    private int i = 0;

    public PlotMPActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_mp);

        // On récupère toutes les données.
        resRequete = this.getIntent().getParcelableArrayListExtra(LISTE_MP_KEY);

        // On isole les températures.
        usageMPY = new Number[resRequete.size()];
        for (i = 0; i < resRequete.size(); i++) {
            int usage = resRequete.get(i).getUmp1() + resRequete.get(i).getUmp2() +
                        resRequete.get(i).getUmp3() + resRequete.get(i).getUmp4() +
                        resRequete.get(i).getUmp5() + resRequete.get(i).getUmp6() +
                        resRequete.get(i).getUmp7() + resRequete.get(i).getUmp8();
            usageMPY[i] = (float) usage / resRequete.get(i).getNbprocs();
        }

        // On récupère le minimum et le maximum des températures pour centrer la courbe par la suite.
        float min = usageMPY[0].floatValue();
        float max = usageMPY[0].floatValue();
        for (i = 0; i < usageMPY.length; i++) {
            if (usageMPY[i].floatValue() < min) {
                min = usageMPY[i].floatValue();
            }
            if (usageMPY[i].floatValue() > max) {
                max = usageMPY[i].floatValue();
            }
        }

        // On définit également l'espace horizontale entre chaque point pour arriver jusqu'à 100 (l'échelle des temps est relative).
        usageMPX = new Number[usageMPY.length];
        int step = 100 / usageMPY.length;
        for (i = 0; i < usageMPY.length; i++) {
            if (i > 0) {
                usageMPX[i] = step + usageMPX[i - 1].floatValue();
            } else {
                usageMPX[i] = step;
            }
        }

        // Divers modifications visuelles du graphique, après son instanciation.
        plot = (XYPlot) findViewById(R.id.plot_mp);
        plot.setTitle("Usages des processeurs du " + resRequete.get(0).getSdate() + " au " + resRequete.get(resRequete.size() - 1).getSdate());
        plot.setRangeLabel("%");
        plot.setDomainLabel("Temps relatif");
        plot.getBorderPaint().setColor(Color.BLACK);
        plot.getBackgroundPaint().setColor(Color.BLACK);
        plot.setDrawingCacheBackgroundColor(Color.BLACK);
        plot.getGraphWidget().getBackgroundPaint().setColor(Color.BLACK);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

        /* On centre la courbe au milieu du graphique en mettant comme frontière à l'axe Y la valeur max + 5 et la valeur min + 5,
         * sauf si le min est inférieur à 5 ou si le max est supérieur à 95, afin de je ne pas avoir des valeurs incohérentes sur l'axe Y
         * en %, comme par exemple -5% ou bien 102%.
         */
        if (min < 5) {
            if (max > 95) {
                plot.setRangeBoundaries(0, 100, BoundaryMode.FIXED);
            } else {
                plot.setRangeBoundaries(0, max + 5, BoundaryMode.FIXED);
            }
        } else {
            if (max > 95) {
                plot.setRangeBoundaries(min - 5, 100, BoundaryMode.FIXED);
            } else {
                plot.setRangeBoundaries(min - 5, max + 5, BoundaryMode.FIXED);
            }
        }

        // On trace la courbe avec ses coordonnées X et Y, et on lui donne un nom.
        XYSeries courbe_mp = new SimpleXYSeries(Arrays.asList(usageMPX), Arrays.asList(usageMPY), "Usage des processeurs en %");

        // On définit la couleur de la courbe, la couleur des points, le transparent sous la courbe, et la couleur des valeurs de chaque point.
        plot.addSeries(courbe_mp, new LineAndPointFormatter(Color.rgb(255, 0, 0), Color.rgb(0, 0, 0), Color.argb(20, 255, 0, 0), new PointLabelFormatter(Color.BLACK)));
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

    public static String getListeMpKey() {
        return LISTE_MP_KEY;
    }
}
