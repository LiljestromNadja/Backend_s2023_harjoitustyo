package hh.harkkas23v03.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoTestingController {

	//testing 
	@GetMapping("/testing")
	public String showTestingPage(Model model) {
		System.out.println("--- Testing endpoint /testing in ArticleController.java");
		
		model.addAttribute("message", "This is a testing page");
		model.addAttribute("message2", "This page is created to demonstrate MVC");
		model.addAttribute("title", "Testing page");
		
		return "testingPage";
	}
	
	//testing2
	@GetMapping("/testing2")
	public ModelAndView showAnotherTestingPage() {
		
		System.out.println("--- Testing endpoint /testing2 in ArticleController.java");
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("message", "This is another endpoint, testing ModelAndView class");
		mv.addObject("message2", "...where we use the same testingPage.html template");
		mv.addObject("title", "Testing page no 2");
		
		mv.setViewName("testingPage");
		
		return mv;
	}
}
