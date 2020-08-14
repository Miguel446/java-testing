package br.com.az.config;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.az.controller.TestController;
import br.com.az.service.HelloService;

@SpringBootTest
class MockitoTests {

	private MockMvc mockMvc;

	@Mock
	private HelloService helloService;

	@InjectMocks
	private TestController testController;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
	}

	@Test
	void testHelloWorld() throws Exception {
		when(helloService.hello()).thenReturn("hello");
		mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("hello"));
		verify(helloService).hello();
	}

	@Test
	void testHelloWorldJson() throws Exception {
		mockMvc.perform(get("/hello/json").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.is("Greetings")))
				.andExpect(jsonPath("$.value", Matchers.is("Hello World!")))
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}

	@Test
	void testPost() throws Exception {
		String json = "{\n" + "  \"name\": \"Greetings\",\n" + "  \"value\": \"Hello World!\"\n" + "}";
		mockMvc.perform(post("/hello/post").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", Matchers.is("Greetings")))
				.andExpect(jsonPath("$.value", Matchers.is("Hello World!")))
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}

}
