package hh.harkkas23v05.domain;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //mariadb
	private long commentid;
	
	
	private String commentmessage;	
	
	@ManyToOne
	@JoinColumn(name = "applicationuserid")
	//@JsonIgnore //Lisätty 6.11
	private ApplicationUser applicationuser; //tämän pitää olla sama ApplicationUser.javassa MappedBy
	
	@ManyToOne
	@JoinColumn(name="articleid")
	private Article article; //tämän pitää olla sama Article.javassa MappedBy
	
	private String commentcreated;
	
	private String wheretocontact;


	public Comment() {
		//super();
	}

	public Comment(
			String commentmessage, 
			ApplicationUser applicationuser, 
			Article article,
			String commentcreated, 
			String wheretocontact) {
		super();
		//this.commentid = commentid;
		this.commentmessage = commentmessage;
		this.applicationuser = applicationuser;
		this.article = article;
		this.commentcreated = commentcreated;
		this.wheretocontact = wheretocontact;
	}

	public long getCommentid() {
		return commentid;
	}

	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}

	public String getCommentmessage() {
		return commentmessage;
	}

	public void setCommentmessage(String commentmessage) {
		if (commentmessage.length() == 0) {
			commentmessage = "Hei! Taisipa tämä käyttäjä unohtaa näppäimistön olemassaolon!";
		}
		this.commentmessage = commentmessage;
		

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
		
		String timeCommented = sldtDate + " " + sldtTime;
		setCommentcreated(timeCommented);
	}

	public ApplicationUser getApplicationuser() {
		return applicationuser;
	}

	public void setApplicationuser(ApplicationUser applicationuser) {
		this.applicationuser = applicationuser;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getCommentcreated() {
		return commentcreated;
	}

	public void setCommentcreated(String commentcreated) {

		this.commentcreated = commentcreated;
	}
	
	

	public String getWheretocontact() {
		return wheretocontact;
	}

	public void setWheretocontact(String wheretocontact) {
		this.wheretocontact = wheretocontact;
	}

	//10-11-2023
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", commentmessage=" + commentmessage + ", commentcreated="
				+ commentcreated + ", wheretocontact=" + wheretocontact + "]";
	}

	
	
	
	/*
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", commentmessage=" + commentmessage + ", commentcreated="
				+ commentcreated + "]";
	}*/
	
	
	
	
	
	
	
	

}
