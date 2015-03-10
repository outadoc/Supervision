package bass.candellier.lefevre.supervision;

/**
 * Created by Yannick on 10/03/2015.
 */
public class UsageDD {
    private String sdate;
    private String Usage;
    private long capacite;
    private long utilisation;

    public UsageDD(String sdate, String usage, long capacite, long utilisation) {
        this.sdate = sdate;
        Usage = usage;
        this.capacite = capacite;
        this.utilisation = utilisation;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getUsage() {
        return Usage;
    }

    public void setUsage(String usage) {
        Usage = usage;
    }

    public long getCapacite() {
        return capacite;
    }

    public void setCapacite(long capacite) {
        this.capacite = capacite;
    }

    public long getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(long utilisation) {
        this.utilisation = utilisation;
    }

    @Override
    public String toString() {
        return "UsageDD{" +
                "sdate='" + sdate + '\'' +
                ", Usage='" + Usage + '\'' +
                ", capacite=" + capacite +
                ", utilisation=" + utilisation +
                '}';
    }
}
