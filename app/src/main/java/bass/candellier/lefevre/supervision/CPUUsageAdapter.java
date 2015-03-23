package bass.candellier.lefevre.supervision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CPUUsageAdapter extends ArrayAdapter<String> {

	public CPUUsageAdapter(Context context, String[] objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.cpu_usage_cell, parent, false);
		}

		TextView usage = (TextView) convertView.findViewById(R.id.lbl_cpu_usage_cell);
		usage.setText(getItem(position) + "%");

		return convertView;
	}

}
