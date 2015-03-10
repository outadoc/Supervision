package bass.candellier.lefevre.supervision;

import java.sql.Connection;

public class ClientSQLmetier {
    private static final String TAG = "";
    private String serveurBDD, nomBDD, userBDD, mdpBDD, portBDD, connexionStringBDD;
    private Connection conn = null;

    public String getServeurBDD() {
        return serveurBDD;
    }

    public void setServeurBDD(String serveurBDD) {
        this.serveurBDD = serveurBDD;
    }

    public String getNomBDD() {
        return nomBDD;
    }

    public void setNomBDD(String nomBDD) {
        this.nomBDD = nomBDD;
    }

    public String getMdpBDD() {
        return mdpBDD;
    }

    public void setMdpBDD(String mdpBDD) {
        this.mdpBDD = mdpBDD;
    }

    public String getPortBDD() {
        return portBDD;
    }

    public void setPortBDD(String portBDD) {
        this.portBDD = portBDD;
    }

    public String getConnexionStringBDD() {
        return connexionStringBDD;
    }

    public void setConnexionStringBDD(String connexionStringBDD) {
        this.connexionStringBDD = connexionStringBDD;
    }

    public String getUserBDD() {
        return userBDD;
    }

    public void setUserBDD(String userBDD) {
        this.userBDD = userBDD;
    }
}
