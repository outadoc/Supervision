package bass.candellier.lefevre.supervision;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientSQLmetier {

    private static final String TAG = "";
    private String serveurBDD, nomBDD, userBDD, mdpBDD, portBDD, connexionStringBDD;
    private Connection conn = null;

    public ClientSQLmetier(String serveurBDD, String nomBDD, String userBDD, String mdpBDD, String portBDD, int timeout) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.setServeurBDD(serveurBDD);
        this.setNomBDD(nomBDD);
        this.setUserBDD(userBDD);
        this.setMdpBDD(mdpBDD);
        this.setPortBDD(portBDD);
        String to = String.valueOf(timeout);
        setConnexionStringBDD("jdbc:jtds:sqlserver://" + getServeurBDD().toString() + ":" + getPortBDD().toString() + "/" + getNomBDD().toString() + ";encrypt=false;instance=SQLEXPRESS;loginTimeout=" + to + ";socketTimeout=" + to + ";");
        Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
        DriverManager.setLoginTimeout(timeout);
    }

    public ResultSet getTableUsageDD(int nb) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(this.connexionStringBDD, this.userBDD, this.mdpBDD);
        }
        Log.i(TAG, "open BDD");
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM ( SELECT * FROM UsageDD ORDER BY date desc ) WHERE rownum <= ?");
        prepstmt.setInt(1, nb);
        ResultSet result = prepstmt.executeQuery();
        if (prepstmt != null) {
            prepstmt.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        return result;
    }

    public ResultSet getTableUsageMP(int nb) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(this.connexionStringBDD, this.userBDD, this.mdpBDD);
        }
        Log.i(TAG, "open BDD");
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM ( SELECT * FROM UsageMP ORDER BY date desc ) WHERE rownum <= ?");
        prepstmt.setInt(1, nb);
        ResultSet result = prepstmt.executeQuery();
        if (prepstmt != null) {
            prepstmt.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        return result;
    }

    public ResultSet getTableTemperatures(int nb) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(this.connexionStringBDD, this.userBDD, this.mdpBDD);
        }
        Log.i(TAG, "open BDD");
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM ( SELECT * FROM Temperatures ORDER BY date desc ) WHERE rownum <= ?");
        prepstmt.setInt(1, nb);
        ResultSet result = prepstmt.executeQuery();
        if (prepstmt != null) {
            prepstmt.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        return result;
    }

    public void finalize() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

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