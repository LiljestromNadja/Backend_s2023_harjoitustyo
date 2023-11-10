package hh.bookstore18.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.bookstore18.domain.ApplicationUser;
import hh.bookstore18.domain.ApplicationUserRepository;
import hh.bookstore18.domain.UserRegistration;
import jakarta.validation.Valid;

@Controller
public class UserRegistrationController {
	
	private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	ApplicationUserRepository regUserRepository; //voisko nimetä fiksummin?! :D 
	
	@GetMapping("/admin/register")
	public String addNewApplicationUser(Model model) {
		log.info("new user template " + new UserRegistration());
		model.addAttribute("newuser", new UserRegistration());
		return "registration";

	}
	
	//tallenna käyttäjä
	@PostMapping("/admin/saveuser")
	//@PostMapping(value= {"/admin/saveuser", "/admin/saveEditedUser"}) 
	public String saveUser(@Valid @ModelAttribute("newuser") UserRegistration newUser, BindingResult bindingResult) {

		log.info("saveuser: newUser is " + newUser.toString());
		if (!bindingResult.hasErrors()) { // validation errors
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { // tarkistetaan täsmäävätkö salasanat
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				ApplicationUser newAppUser = new ApplicationUser();	
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setFirstname(newUser.getFirstname()); //MariaDb, jos näitä ei ole niin herjaa Column 'firstname' cannot be null
				newAppUser.setLastname(newUser.getLastname()); //MariaDb, jos näitä ei ole niin herjaa Column 'lastname' cannot be null
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole(newUser.getRole());
				//newAppUser.setRole("USER"); //Asetetaan user role USERiksi MUISTA vaihtaa myös UserRegistration.java
				if (regUserRepository.findByUsername(newUser.getUsername()) == null) { // onko käyttäjänimi jo käytössä
					System.out.println("LISÄTTY KÄYTTÄJÄ: " + newAppUser);
					regUserRepository.save(newAppUser);
				} else {
					System.out.println("KÄYTTÄJÄTUNNUS ON JO OLEMASSA");
					//log.info("username already exists");
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "registration";
				}
			} else {
				log.info("Password doesn't match");
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "registration";
			}
		} else {
			return "registration";
		}		
		//return "redirect:/login"; //jos onnistuu, kirjaa ÄRSYTTÄVÄSTI ULOS
		return "redirect:../admin/userlist"; 
	}
	
	
	//Listaa kaikki käyttäjät	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = {"/admin/userlist"}) //endpoint: http://localhost:8080/ ja  http://localhost:8080/booklist
	public String userlist(Model model) {	
		System.out.println("LISTATAAN KAIKKI KÄYTTÄJÄT --- UserRegistrationController.java");
		model.addAttribute("users", regUserRepository.findAll());		
		return "userlist";	
	}
	//Muokkaa käyttäjää
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value= "admin/editUser/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		System.out.println("MUOKATAAN KÄYTTÄJÄÄ, id: " + id +" --- UserRegistrationController.java");
		model.addAttribute("appuser", regUserRepository.findById(id)); //user id
		//model.addAttribute("roles", uRoleRepository.findAll());
		return "editUser";
	}
		
		
	//Poista käyttäjä
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/admin/deleteuser/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		regUserRepository.deleteById(id);
		System.out.println("POISTETTU KÄYTTÄJÄ, id: " + id + " --- UserRegistrationController.java");
		return "redirect:../userlist";
	}
	

}
