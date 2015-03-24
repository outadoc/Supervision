package bass.candellier.lefevre.supervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayUsageTempAdapter extends ArrayAdapter<Temp> { // Déclaration d'une liste d'items
    private ArrayList<Temp> objets;
    private int item_id;

    public ArrayUsageTempAdapter(Context context, int textViewResourceId, ArrayList<Temp> objects) {
        super(context, textViewResourceId, objects);
        this.objets = objects;
        this.item_id = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.temp_list, null);
        }
        Temp tCourant = objets.get(position);
        if (tCourant != null) {
            TextView tv_temp = (TextView) v.findViewById(R.id.temp);
            TextView tv_sdate = (TextView) v.findViewById(R.id.sdate);
            TextView tv_nomBaie = (TextView) v.findViewById(R.id.nomBaie);

            ImageView icone = (ImageView) v.findViewById(R.id.imgThermometre);
            if (tv_temp != null) tv_temp.setText(String.valueOf(tCourant.getTemp()) + "°C");
            if (tv_sdate != null) tv_sdate.setText(tCourant.getSdate());
            if (tv_nomBaie != null) tv_nomBaie.setText(tCourant.getNomBaie());

            if (icone != null) icone.setImageResource(R.drawable.thermometre);
        }
        return v;
    }
}




