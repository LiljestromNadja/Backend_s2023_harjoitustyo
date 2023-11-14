package hh.harkkas23v06;

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
		
		System.out.println("HarkkaRestTest --- testing endpoint index --- Expect status ok");
		
		mockMvc.perform(get("/index")).andExpect(status().isOk());

	}
	
	@Test
	public void responseTypeApplicationJson() throws Exception {
		System.out.println("HarkkaRestTest --- testing if endpoint /articlesjson has content type JSON --- Expect status ok");
		mockMvc.perform(get("/articlesjson"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test
	public void responseTypeApplicationJsonFail() throws Exception {		
		System.out.println("HarkkaRestTest --- testing if endpoint /articlelist has content type JSON --- Expecting FAIL");
		
		mockMvc.perform(get("/articlelist")) //fail koska vastaus ei ole jsonia
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void sbApiStatusOk() throws Exception {
		mockMvc.perform(get("/api/articles")).andExpect(status().isOk());
		
		System.out.println("HarkkaRestTest --- testing sb api --- Expecting status ok");	
		
	}
	
	@Test
	public void sbApiMemoStatusOk() throws Exception {
		mockMvc.perform(get("/api/comments")).andExpect(status().isOk());
		
		System.out.println("HarkkaRestTest --- testing sb api memos --- Expecting status ok");	
		
	}
	
	@Test
	public void sbOneItemApiStatusOk() throws Exception {
		mockMvc.perform(get("/api/articles/1")).andExpect(status().isOk());
		
		System.out.println("testing sb api article id 1  --- status ok");	
		
	}

}
