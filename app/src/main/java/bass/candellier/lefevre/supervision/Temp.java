package bass.candellier.lefevre.supervision;

import android.os.Parcel;
import android.os.Parcelable;

public class Temp implements Parcelable {

    private String sdate;
    private String temp;
    private String nomBaie;
    public static final Parcelable.Creator<Temp> CREATOR = new Parcelable.Creator<Temp>() {
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

    public Temp(String sdate, String temp, String nomBaie) {
        this.sdate = sdate;
        this.temp = temp;
        this.nomBaie = nomBaie;
    }

    public Temp(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public String toString() {
        return "TEMP{" +
                "sdate='" + sdate + '\'' +
                ", temp='" + temp + '\'' +
                ", nomBaie='" + nomBaie + '\'' +
                '}';
    }

    private void readFromParcel(Parcel in) {
        this.sdate = in.readString();
        this.temp = in.readString();
        this.nomBaie = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sdate);
        dest.writeString(this.temp);
        dest.writeString(this.nomBaie);
    }

    public int describeContents() {
        return 0;
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
