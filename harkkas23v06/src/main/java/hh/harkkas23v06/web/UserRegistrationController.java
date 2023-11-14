package hh.harkkas23v06.web;

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

import hh.harkkas23v06.domain.ApplicationUser;
import hh.harkkas23v06.domain.ApplicationUserRepository;
import hh.harkkas23v06.domain.UserRegistration;
import jakarta.validation.Valid;

@Controller
public class UserRegistrationController {
	

	@Autowired
	ApplicationUserRepository appUserRepository; 
	
	//Uusien käyttäjien lisääminen
	//Vain ADMIN
	@GetMapping("/admin/register")
	public String addNewApplicationUser(Model model) {
		System.out.println("--- new user template" + new UserRegistration() + " --- UserRegistrationController --- ");

		model.addAttribute("newuser", new UserRegistration());
		return "registration";

	}
	
	//Tallenna käyttäjä
	//Vain ADMIN
	@PostMapping("/admin/saveuser")
	//@PostMapping(value= {"/admin/saveuser", "/admin/saveEditedUser"}) 
	public String saveUser(@Valid @ModelAttribute("newuser") UserRegistration newUser, BindingResult bindingResult) {

		System.out.println("--- saveUser: newUser is: " + newUser.toString() + " --- UserRegistrationController --- ");

		if (!bindingResult.hasErrors()) { // jos ei tule validointivirheitä
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { // tarkistetaan täsmäävätkö salasanat
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				ApplicationUser newAppUser = new ApplicationUser();	
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setFirstname(newUser.getFirstname()); 
				newAppUser.setLastname(newUser.getLastname()); 
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole(newUser.getRole());
				//newAppUser.setRole("USER"); //Asetetaan user role USERiksi, jos muokkaat niin MUISTA vaihtaa myös UserRegistration.java
				if (appUserRepository.findByUsername(newUser.getUsername()) == null) { // onko käyttäjänimi jo käytössä
					System.out.println("LISÄTTY KÄYTTÄJÄ: " + newAppUser + " --- UserRegistrationController --- ");
					appUserRepository.save(newAppUser);
				} else {
					System.out.println("--- KÄYTTÄJÄTUNNUS ON JO OLEMASSA --- UserRegistrationController --- ");

					bindingResult.rejectValue("username", "err.username", "Käyttäjänimi on jo olemassa");
					return "registration";
				}
			} else {
				System.out.println("SALASANAT EIVÄT TÄSMÄÄ --- UserRegistrationController --- ");

				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Salasanat eivät täsmää");
				return "registration";
			}
		} else {
			return "registration";
		}		
		//return "redirect:/login"; //jos tämä käytössä, kirjaa senhetkisen käyttäjän ulos
		return "redirect:../admin/userlist"; 
	}
	
	
	//Listaa kaikki käyttäjät	
	//ADMIN
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = {"/admin/userlist"}) //endpoint: http://localhost:8080/admin/userlist 
	public String userlist(Model model) {	
		System.out.println("--- LISTATAAN KAIKKI KÄYTTÄJÄT --- UserRegistrationController.java ---");
		model.addAttribute("users", appUserRepository.findAll());		
		return "userlist";	
	}
	
	
	//Muokkaa käyttäjää
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus, onko oikeus muokata
	@RequestMapping(value= "admin/editUser/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id, Model model) {
		System.out.println("--- MUOKATAAN KÄYTTÄJÄÄ, id: " + id +" --- UserRegistrationController.java ---");
		model.addAttribute("appuser", appUserRepository.findById(id)); //user id
		//model.addAttribute("roles", uRoleRepository.findAll());
		return "editUser";
	}
		
		
	//Poista käyttäjä
	@PreAuthorize("hasAuthority('ADMIN')") //metoditason tarkistus onko oikeus poistaa 
	@RequestMapping(value="/admin/deleteuser/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		appUserRepository.deleteById(id);
		System.out.println("--- POISTETTU KÄYTTÄJÄ, id: " + id + " --- UserRegistrationController.java ---");
		return "redirect:../userlist";
	}
	

}
