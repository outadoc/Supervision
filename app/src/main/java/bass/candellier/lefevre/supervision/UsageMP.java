package bass.candellier.lefevre.supervision;

public class UsageMP {
    private String sdate;
    private int nbprocs;
    private int ump1;
    private int ump2;
    private int ump3;
    private int ump4;
    private int ump5;
    private int ump6;
    private int ump7;
    private int ump8;

    public UsageMP(String sdate, int nbprocs, int ump1, int ump2, int ump3, int ump4, int ump5, int ump6, int ump7, int ump8) {
        this.sdate = sdate;
        this.nbprocs = nbprocs;
        this.ump1 = ump1;
        this.ump2 = ump2;
        this.ump3 = ump3;
        this.ump4 = ump4;
        this.ump5 = ump5;
        this.ump6 = ump6;
        this.ump7 = ump7;
        this.ump8 = ump8;
    }

    public int describeContents(){


        return 0;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public int getNbprocs() {
        return nbprocs;
    }

    public void setNbprocs(int nbprocs) {
        this.nbprocs = nbprocs;
    }

    public int getUmp1() {
        return ump1;
    }

    public void setUmp1(int ump1) {
        this.ump1 = ump1;
    }

    public int getUmp2() {
        return ump2;
    }

    public void setUmp2(int ump2) {
        this.ump2 = ump2;
    }

    public int getUmp3() {
        return ump3;
    }

    public void setUmp3(int ump3) {
        this.ump3 = ump3;
    }

    public int getUmp4() {
        return ump4;
    }

    public void setUmp4(int ump4) {
        this.ump4 = ump4;
    }

    public int getUmp5() {
        return ump5;
    }

    public void setUmp5(int ump5) {
        this.ump5 = ump5;
    }

    public int getUmp6() {
        return ump6;
    }

    public void setUmp6(int ump6) {
        this.ump6 = ump6;
    }

    public int getUmp7() {
        return ump7;
    }

    public void setUmp7(int ump7) {
        this.ump7 = ump7;
    }

    public int getUmp8() {
        return ump8;
    }

    public void setUmp8(int ump8) {
        this.ump8 = ump8;
    }

    @Override
    public String toString() {
        return "UsageMP{" +
                "sdate='" + sdate + '\'' +
                ", nbprocs=" + nbprocs +
                ", ump1=" + ump1 +
                ", ump2=" + ump2 +
                ", ump3=" + ump3 +
                ", ump4=" + ump4 +
                ", ump5=" + ump5 +
                ", ump6=" + ump6 +
                ", ump7=" + ump7 +
                ", ump8=" + ump8 +
                '}';
    }

}
