package hh.harkkas23v01.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class City {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO) //h2
	@GeneratedValue(strategy = GenerationType.IDENTITY) //mariadb
	private Long cityid;
	
	@Size(min = 1, max = 30, message="Kaupungin nimessä on oltava vähintään yksi kirjain")
	@Column(name = "name", unique = true) //18102023	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city") //mappedByn pitää olla sama kuin Article.javassa private City city;
	@JsonIgnore
	private List<Article> articles;

	public City() {}
	
	public City(String name) {  //HUOM, tarkkana constructoreiden kanssa!
		super();
		this.name = name;
		
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "City [cityid=" + cityid + ", name=" + name + ", articles=" + articles + "]";
	}
	
	
	
	
	
	
	

	
	

}
