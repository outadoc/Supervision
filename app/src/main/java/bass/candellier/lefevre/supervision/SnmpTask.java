package bass.candellier.lefevre.supervision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.adventnet.snmp.beans.SnmpServer;
import com.adventnet.snmp.beans.SnmpTarget;

public abstract class SnmpTask extends AsyncTask<String[], Void, Void> {

	protected Context context;
	protected SharedPreferences prefs;
	protected SnmpTarget snmpTarget;
	protected SnmpTaskListener listener;

	private String[] results;

	public SnmpTask(Context context, SnmpTaskListener listener) {
		this.context = context;
		this.listener = listener;

		this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
		this.snmpTarget = new SnmpTarget();

		snmpTarget.setSnmpVersion(SnmpServer.VERSION1);
		snmpTarget.setTimeoutInMilliSec(10000);
		snmpTarget.setRetries(2);
	}

	@Override
	protected Void doInBackground(String[]... params) {
		snmpTarget.setObjectIDList(params[0]);
		results = snmpTarget.snmpGetList();
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		snmpTarget.releaseResources();
		listener.onResult(results);
	}
}
