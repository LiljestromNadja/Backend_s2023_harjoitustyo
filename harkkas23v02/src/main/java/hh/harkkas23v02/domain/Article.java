package hh.harkkas23v02.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

//import jakarta.validation.constraints.Size;

@Entity
public class Article {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) //h2
	@GeneratedValue(strategy=GenerationType.IDENTITY) //mariadb
	private long id;
	
	@NotEmpty(message = "Anna ilmoitukselle kuvaava otsikko!")
	//@Size(min = 1, max = 300)
	private String title;
	
	@NotEmpty(message = "Anna myytävälle kohteelle kuvaus!")
	//@Size(min = 1, max = 300)
	private String description;
	
	@NotEmpty(message = "Anna ilmoituksen julkaisijan nimi tai puhelinnumero!")
	//@Size(min = 1, max = 100)
	private String publisher;	
	
	private String dateadded;	

	@PositiveOrZero(message = "Tuotteen hinta on oltava 0 tai enemmän!")
	private double price;
	
    @ManyToOne
    @JoinColumn(name = "adtypeid")
    private Adtype adtype;	 //<-----------Tämän pitää olla sama kuin Adtype.javassa mappedBy = "adtype"
    
    
    @ManyToOne
    @JoinColumn(name = "applicationuserid")
    @JsonIgnore //lisätty 6.11
    private ApplicationUser applicationuser;	 //<-----------Tämän pitää olla sama kuin ApplicationUser.javassa mappedBy = "applicationuser"
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article") //MappedByn pitää olla sama kuin Comment.javassa private Article article
    @JsonIgnore        
    private List<Comment> comments;
    

	
	public Article() {} // HUOM, tarkkana constructoreiden kanssa!
	

	public Article(String title, String description, String publisher, String dateadded, int publicationYear, @PositiveOrZero(message = "Tuotteen hinta on oltava 0 tai enemmän!") double price) {
		super();
		this.title = title;
		this.description = description;
		this.publisher = publisher;
		this.dateadded = dateadded;
		this.price = price;
	}
	
	public Article(String title, String description, String publisher, String dateadded, int publicationYear,@PositiveOrZero(message = "Tuotteen hinta on oltava 0 tai enemmän!") double price, Adtype adtype) {
		super();
		this.title = title;
		this.description = description;
		this.publisher = publisher;
		this.dateadded = dateadded;
		this.price = price;
		this.adtype = adtype;
	}

	public Article(long id, @NotEmpty(message = "Anna ilmoitukselle kuvaava otsikko!") String title,@NotEmpty(message = "Anna myytävälle kohteelle kuvaus!") String description,
			@NotEmpty(message = "Anna ilmoituksen julkaisijan nimi!") String publisher, String dateadded,
			@PositiveOrZero(message = "Tuotteen hinta on oltava 0 tai enemmän!") double price, Adtype adtype, ApplicationUser applicationuser) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.publisher = publisher;
		this.dateadded = dateadded;
		this.price = price;
		this.adtype = adtype;
		this.applicationuser = applicationuser;
	}


	
	
	
	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public ApplicationUser getApplicationuser() {
		return applicationuser;
	}


	public void setApplicationuser(ApplicationUser applicationuser) {
		this.applicationuser = applicationuser;
	}


	public Adtype getAdtype() {
		return adtype;
	}
	public void setAdtype(Adtype adtype) {
		this.adtype = adtype;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		
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
		
		String timeAdded = sldtDate + " " + sldtTime;
		setDateadded(timeAdded);
		
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDateadded() {
		return dateadded;
	}
	public void setDateadded(String dateadded) {
		this.dateadded = dateadded;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", publisher=" + publisher
				+ ", dateadded=" + dateadded + ", price=" + price + "]";
	}


	
	/*
	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", publisher=" + publisher + ", dateadded="
				+ dateadded + ", price=" + price + "]";
	}*/
	
	
	
	
	
	

	
	
	

}
