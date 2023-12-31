package hh.harkkas23v06.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Note! This class is not an entity class. Class is used when creating new
 * application user.
 * 
 * ApplicationUser entity is used when user will be saved to db.
 */

public class UserRegistration { 
	
	@NotEmpty(message="Ei voi olla tyhjä")
	@Size(min = 1, message="Etunimessä on oltava vähintään 1 kirjain")
	private String firstname = "" ; 
	
	@NotEmpty(message="Ei voi olla tyhjä")
	@Size(min = 1, message="Sukunimessä on oltava vähintään 1 kirjain")
	private String lastname = "" ; 
	
	@NotEmpty(message="Ei voi olla tyhjä")
	@Size(min = 4, message="Käyttäjänimen on oltava vähintään 4 merkkiä pitkä")
	private String username = "";

	@NotEmpty(message="Ei voi olla tyhjä")
	@Size(min = 4, max = 30, message="Salasanan on oltava 4-30 merkin pituinen")
	private String password = "";

	@NotEmpty(message="Ei voi olla tyhjä")
	@Size(min = 4, max = 30, message="Salasanan on oltava 4-30 merkin pituinen")
	private String passwordCheck = "";

	@NotEmpty(message="Ei voi olla tyhjä")
	//private String role = "USER"; //muista vaihtaa myös UserRegistrationControllerissa
	private String role = "";

	public UserRegistration() {
		super();
	}
	
	public UserRegistration(
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 1, message = "Etunimessä on oltava vähintään 1 kirjain") String firstname,
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 1, message = "Sukunimessä on oltava vähintään 1 kirjain") String lastname,
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 4, message = "Käyttäjänimen on oltava vähintään 4 merkkiä pitkä") String username,
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 4, max = 30, message = "Salasanan on oltava 4-30 merkin pituinen") String password,
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 4, max = 30, message = "Salasanan on oltava 4-30 merkin pituinen") String passwordCheck,
			@NotEmpty(message="Ei voi olla tyhjä") String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.role = role;
	}
	
	public UserRegistration(
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 4, message = "Käyttäjänimen on oltava vähintään 4 merkkiä pitkä") String username,
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 4, max = 30, message = "Salasanan on oltava 4-30 merkin pituinen") String password,
			@NotEmpty(message = "Ei voi olla tyhjä") @Size(min = 4, max = 30, message = "Salasanan on oltava 4-30 merkin pituinen") String passwordCheck) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRegistration [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", passwordCheck=" + passwordCheck + ", role=" + role + "]";
	}
	
	


}
