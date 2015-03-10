package bass.candellier.lefevre.supervision;

/**
 * Created by Yannick on 10/03/2015.
 */
public class TEMP {

    private String sdate;
    private String temp;
    private String nomBaie;


    public TEMP(String sdate, String temp, String nomBaie) {
        this.sdate = sdate;
        this.temp = temp;
        this.nomBaie = nomBaie;
    }

    public int describeContents(){


        return 0;
    }


    @Override
    public String toString() {
        return "TEMP{" +
                "sdate='" + sdate + '\'' +
                ", temp='" + temp + '\'' +
                ", nomBaie='" + nomBaie + '\'' +
                '}';
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getNomBaie() {
        return nomBaie;
    }

    public void setNomBaie(String nomBaie) {
        this.nomBaie = nomBaie;
    }
}
