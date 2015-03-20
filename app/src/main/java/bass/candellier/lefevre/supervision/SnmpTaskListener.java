package bass.candellier.lefevre.supervision;

/**
 * Created by outadoc on 20/03/15.
 */
public interface SnmpTaskListener {

	/**
	 * Appelée quand le serveur SNMP a répondu avec les valeurs.
	 *
	 * @param vars les valeurs, sous forme de chaînes, retournées par le serveur
	 */
	public void onResult(String[] vars);

}
