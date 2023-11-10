package hh.bookstore18.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_users")
public class ApplicationUser {
	
	//ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;  
    
    @Column(name="firstname")
    private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    
    //Uniikki käyttäjätunnus
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    //Salasana
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    //Käyttäjän rooli
    @Column(name = "role", nullable = false)
    private String role;
    

    /* Käyttäjäroolien määrittäminen enumin avulla
    public enum UserRole {
    	USER,
    	ADMIN
    }
    
    private UserRole role;
    */
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationuser") //mappedByn pitää olla sama kuin Book.javassa private ApplicationUser applicationuser;
	@JsonIgnore
	private List<Book> books;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationuser") //MappedByn pitää olla sama kuin Comment.javassa private ApplicationUser applicationuser
    @JsonIgnore
    private List<Comment> comments;

    
    
    
    //Constructors  
	public ApplicationUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationUser(String firstname, String lastname, String username, String passwordHash, String role) {
		super();
		//Mariadb
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public ApplicationUser(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	

	//get&set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", passwordHash=" + passwordHash + ", role=" + role + ", books=" + books + "]";
	}


	/*
	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", passwordHash=" + passwordHash + ", role=" + role + "]";
	}*/

	
	
	
	/*
	//toString
	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role="
				+ role + "]";
	}    */
    
	
    
}
