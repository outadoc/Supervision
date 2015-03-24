package bass.candellier.lefevre.supervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yannick on 24/03/2015.
 */

public class ArrayUsageMPAdapter extends ArrayAdapter<UsageMP> {

	public ArrayUsageMPAdapter(Context context, int textViewResourceId, ArrayList<UsageMP> objects) {
		super(context, textViewResourceId, objects);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.cpu_list, null);
		}

		UsageMP uCourant = getItem(position);

		if(uCourant != null) {
			TextView tv_MP1 = (TextView) convertView.findViewById(R.id.cpu1);
			TextView tv_MP2 = (TextView) convertView.findViewById(R.id.cpu2);
			TextView tv_MP3 = (TextView) convertView.findViewById(R.id.cpu3);
			TextView tv_MP4 = (TextView) convertView.findViewById(R.id.cpu4);
			TextView tv_MP5 = (TextView) convertView.findViewById(R.id.cpu5);
			TextView tv_MP6 = (TextView) convertView.findViewById(R.id.cpu6);
			TextView tv_MP7 = (TextView) convertView.findViewById(R.id.cpu7);
			TextView tv_MP8 = (TextView) convertView.findViewById(R.id.cpu8);

			TextView tv_sdate = (TextView) convertView.findViewById(R.id.sdate);

			tv_MP1.setText(String.valueOf(uCourant.getUmp1()) + "%");
			tv_MP2.setText(String.valueOf(uCourant.getUmp2()) + "%");
			tv_MP3.setText(String.valueOf(uCourant.getUmp3()) + "%");
			tv_MP4.setText(String.valueOf(uCourant.getUmp4()) + "%");
			tv_MP5.setText(String.valueOf(uCourant.getUmp5()) + "%");
			tv_MP6.setText(String.valueOf(uCourant.getUmp6()) + "%");
			tv_MP7.setText(String.valueOf(uCourant.getUmp7()) + "%");
			tv_MP8.setText(String.valueOf(uCourant.getUmp8()) + "%");

			tv_sdate.setText(uCourant.getSdate());
		}

		return convertView;
	}

}
