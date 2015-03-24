package bass.candellier.lefevre.supervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayUsageTempAdapter extends ArrayAdapter<Temp> {

	public ArrayUsageTempAdapter(Context context, int textViewResourceId, ArrayList<Temp> objects) {
		super(context, textViewResourceId, objects);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.temp_list, null);
		}

		Temp tCourant = getItem(position);

		if(tCourant != null) {
			TextView tv_temp = (TextView) convertView.findViewById(R.id.temp);
			TextView tv_sdate = (TextView) convertView.findViewById(R.id.sdate);
			TextView tv_nomBaie = (TextView) convertView.findViewById(R.id.nomBaie);

			ImageView icone = (ImageView) convertView.findViewById(R.id.imgThermometre);

			tv_temp.setText(String.valueOf(tCourant.getTemp()) + "°C");
			tv_sdate.setText(tCourant.getSdate());
			tv_nomBaie.setText(tCourant.getNomBaie());

			icone.setImageResource(R.drawable.thermometre);
		}
		return convertView;
	}

}




