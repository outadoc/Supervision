package bass.candellier.lefevre.supervision;

/**
 * Created by Yannick on 24/03/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ArrayUsageDDAdapter extends ArrayAdapter<UsageDD> {


	public ArrayUsageDDAdapter(Context context, int textViewResourceId, ArrayList<UsageDD> objects) {
		super(context, textViewResourceId, objects);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.dd_list, null);
		}

		UsageDD uCourant = getItem(position);

		if(uCourant != null) {
			TextView tv_DD = (TextView) convertView.findViewById(R.id.usage);
			TextView tv_sdate = (TextView) convertView.findViewById(R.id.sdate);
			TextView tv_nomBaie = (TextView) convertView.findViewById(R.id.libre);

			tv_DD.setText(String.valueOf(uCourant.getUsage()) + "%");
			tv_sdate.setText(uCourant.getSdate());
			tv_nomBaie.setText(String.valueOf(Math.round((uCourant.getCapacite() - uCourant.getUtilisation()) / 1000000)) + " " +
					"Mo");

		}

		return convertView;
	}

}




