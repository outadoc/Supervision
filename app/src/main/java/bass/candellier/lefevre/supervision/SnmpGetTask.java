package bass.candellier.lefevre.supervision;

import android.content.Context;

/**
 * Created by outadoc on 20/03/15.
 */
public class SnmpGetTask extends SnmpTask {

	public static final String OID_HDD_UA = ".1.3.6.1.2.1.25.2.3.1.4.1";
	public static final String OID_HDD_CAPACITY = ".1.3.6.1.2.1.25.2.3.1.5.1";
	public static final String OID_HDD_USAGE = ".1.3.6.1.2.1.25.2.3.1.6.1";
	public static final String OID_BASE_CPU_USAGE = ".1.3.6.1.2.1.25.3.3.1.2.";

	public static final int NB_CPU_CORES = 8;

	public SnmpGetTask(Context context, SnmpTaskListener listener) {
		super(context, listener);
	}

	@Override
	protected void onPreExecute() {
		snmpTarget.setTargetHost(prefs.getString("pref_snmp_hostname", "82.233.223.249"));
		snmpTarget.setTargetPort(prefs.getInt("pref_snmp_port", 161));
		snmpTarget.setCommunity(prefs.getString("pref_snmp_password", "DataCenterVDR"));
	}

}
