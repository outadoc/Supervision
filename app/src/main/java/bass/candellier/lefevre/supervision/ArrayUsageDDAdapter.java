package bass.candellier.lefevre.supervision;

/**
 * Created by Yannick on 24/03/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayUsageDDAdapter extends ArrayAdapter<UsageDD> { // Déclaration d'une liste d'items
    private ArrayList<UsageDD> objets;
    private int item_id;

    public ArrayUsageDDAdapter(Context context, int textViewResourceId, ArrayList<UsageDD> objects) {
        super(context, textViewResourceId, objects);
        this.objets = objects;
        this.item_id = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.dd_list, null);
        }
        UsageDD uCourant = objets.get(position);
        if (uCourant != null) {
            TextView tv_DD = (TextView) v.findViewById(R.id.usage);
            TextView tv_sdate = (TextView) v.findViewById(R.id.sdate);
            TextView tv_nomBaie = (TextView) v.findViewById(R.id.libre);

            ImageView icone = (ImageView) v.findViewById(R.id.imgThermometre);
            if (tv_DD != null) tv_DD.setText(String.valueOf(uCourant.getUsage())+"%");
            if (tv_sdate != null) tv_sdate.setText(uCourant.getSdate());
            if (tv_nomBaie != null) tv_nomBaie.setText(String.valueOf(Math.round((uCourant.getCapacite()-uCourant.getUtilisation())/1000000))+"Mo");

            //if (icone != null) icone.setImageResource(R.drawable.thermometre);
        }
        return v;
    }
}




