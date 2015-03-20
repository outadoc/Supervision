package bass.candellier.lefevre.supervision;

import android.content.Context;

/**
 * Created by outadoc on 20/03/15.
 */
public class SnmpGetTaskSonde extends SnmpTask {

	public static final String OID_SONDE_TEMP = ".1.3.6.1.4.1.21796.4.1.3.1.4.1";
	public static final String OID_SONDE_TEMP_FLOAT = ".1.3.6.1.4.1.21796.4.1.3.1.5.1";
	public static final String OID_SONDE_TEMP_UNIT = ".1.3.6.1.4.1.21796.4.1.3.1.7.1";
	public static final String OID_SONDE_STATE = ".1.3.6.1.4.1.21796.4.1.3.1.3.1";

	public SnmpGetTaskSonde(Context context, SnmpTaskListener listener) {
		super(context, listener);
	}

	@Override
	protected void onPreExecute() {
		snmpTarget.setTargetHost(prefs.getString("pref_snmp_temp_hostname", "82.233.223.249"));
		snmpTarget.setTargetPort(prefs.getInt("pref_snmp_temp_port", 1610));
		snmpTarget.setCommunity(prefs.getString("pref_snmp_temp_password", "DataCenterVDR"));
	}

}
