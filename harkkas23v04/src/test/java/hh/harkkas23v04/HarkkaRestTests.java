package hh.harkkas23v04;

//mm. mockMvc:n get- ja post-metodit
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class HarkkaRestTests {
	
	@Autowired
	private WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach //JUnit5
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	
	@Test
	public void statusOk() throws Exception {
		
		System.out.println("BookRestTest --- testing endpoint booklist --- status ok");
		
		mockMvc.perform(get("/booklist")).andExpect(status().isOk());

	}
	
	@Test
	public void responseTypeApplicationJson() throws Exception {
		System.out.println("BookRestTest --- testing if endpoint /booksjson has content type JSON --- status ok");
		mockMvc.perform(get("/booksjson"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test
	public void responseTypeApplicationJsonFail() throws Exception {		
		System.out.println("BookRestTest --- testing if endpoint /booklis has content type JSON --- FAIL");
		
		mockMvc.perform(get("/booklist")) //fail koska vastaus ei ole jsonia
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void sbApiStatusOk() throws Exception {
		mockMvc.perform(get("/api/books")).andExpect(status().isOk());
		
		System.out.println("testing sb api --- status ok");	
		
	}
	
	@Test
	public void sbOneItemApiStatusOk() throws Exception {
		mockMvc.perform(get("/api/books/1")).andExpect(status().isOk());
		
		System.out.println("testing sb api book id 1  --- status ok");	
		
	}

}
