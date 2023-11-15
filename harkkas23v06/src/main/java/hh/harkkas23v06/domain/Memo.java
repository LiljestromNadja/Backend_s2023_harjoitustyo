package hh.harkkas23v06.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Memo {
	
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) //h2
	@GeneratedValue(strategy=GenerationType.IDENTITY) //mariadb
	private long memoid;
	
	@NotEmpty(message = "Memo ei voi olla tyhjä!")
	private String memocontent;
	
	private String memodate;
	
	@ManyToOne
    @JoinColumn(name = "applicationuserid")
    //@JsonIgnore //lisätty 11.11
    private ApplicationUser applicationuser;	 //<-----------Tämän pitää olla sama kuin ApplicationUser.javassa mappedBy = "applicationuser"

	
	public Memo() {
		super();
	}


	public Memo(String memocontent, String memodate, ApplicationUser applicationuser) {
		super();
		//this.memoid = memoid;
		this.memocontent = memocontent;
		this.memodate = memodate;
		this.applicationuser = applicationuser;
	}

	
//	13.11
	public Memo(long memoid, String memocontent, String memodate, ApplicationUser applicationuser) {
		super();
		this.memoid = memoid;
		this.memocontent = memocontent;
		this.memodate = memodate;
		this.applicationuser = applicationuser;
	}


	public long getMemoid() {
		return memoid;
	}


	public void setMemoid(long memoid) {
		this.memoid = memoid;
	}


	public String getMemocontent() {
		return memocontent;
	}


	public void setMemocontent(String memocontent) {
		this.memocontent = memocontent;
		// kellonaika
		int min = LocalDateTime.now().getMinute();
		int h = LocalDateTime.now().getHour();
		int secs = LocalDateTime.now().getSecond();

		String sMin;
		String sH;
		String sSecs;

		if (min < 10) {
			sMin = "0" + LocalDateTime.now().getMinute();
		} else {
			sMin = LocalDateTime.now().getMinute() + "";
		}

		if (h < 10) {
			sH = "0" + LocalDateTime.now().getHour();
		} else {
			sH = LocalDateTime.now().getHour() + "";
		}
		if (secs < 10) {
			sSecs = "0" + LocalDateTime.now().getSecond();
		} else {
			sSecs = LocalDateTime.now().getSecond() + "";
		}

		String sldtTime = sH + ":" + sMin + ":" + sSecs;
		//System.out.println(sldtTime);

		// pvm
		String yyyyString = LocalDateTime.now().getYear() + "-";
		int dd = LocalDateTime.now().getDayOfMonth();
		int mm = LocalDateTime.now().getMonthValue();
		String ddString;
		String mmString;

		if (dd < 10) {
			ddString = "0" + LocalDateTime.now().getDayOfMonth() + "";
		} else {
			ddString = LocalDateTime.now().getDayOfMonth() + "";
		}

		if (mm < 10) {
			mmString = "0" + LocalDateTime.now().getMonthValue() + "-";
		} else {
			mmString = LocalDateTime.now().getMonthValue() + "-";
		}
		String sldtDate = yyyyString + mmString + ddString;
		//System.out.println(sldtDate);
		
		String memoCreated = sldtDate + " " + sldtTime;
		setMemodate(memoCreated);

	}


	public String getMemodate() {
		return memodate;
	}


	public void setMemodate(String memodate) {
		this.memodate = memodate;
	}


	public ApplicationUser getApplicationuser() {
		return applicationuser;
	}


	public void setApplicationuser(ApplicationUser applicationuser) {
		this.applicationuser = applicationuser;
	}


	@Override
	public String toString() {
		return "Memo [memoid=" + memoid + ", memocontent=" + memocontent + ", memodate=" + memodate + "]";
	}
	
	

	
	

	
	
}
