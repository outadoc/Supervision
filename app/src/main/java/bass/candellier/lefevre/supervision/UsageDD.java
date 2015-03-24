package bass.candellier.lefevre.supervision;

import android.os.Parcel;
import android.os.Parcelable;

public class UsageDD implements Parcelable {

	public static final Parcelable.Creator<UsageDD> CREATOR = new Parcelable.Creator<UsageDD>() {
		public UsageDD createFromParcel(Parcel in) {
			return new UsageDD(in);
		}

		public UsageDD[] newArray(int size) {
			return new UsageDD[size];
		}
	};
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

	public UsageDD(Parcel in) {
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in) {
		this.sdate = in.readString();
		this.Usage = in.readString();
		this.capacite = in.readLong();
		this.utilisation = in.readLong();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.sdate);
		dest.writeString(this.Usage);
		dest.writeLong(this.capacite);
		dest.writeLong(this.utilisation);
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
