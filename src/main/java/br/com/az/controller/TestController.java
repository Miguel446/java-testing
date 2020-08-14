package br.com.az.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.az.service.HelloService;

@RestController
@RequestMapping("/hello")
public class TestController {

	private HelloService helloService;

	public TestController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping
	public String helloWorld() {
		return helloService.hello();
	}

	@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hello json() {
		return new Hello("Greetings", "Hello World!");
	}

	@PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Hello post(@RequestBody Hello hello) {
		return hello;
	}

	private static class Hello {
		String name;
		String value;

		Hello(String name, String value) {
			this.name = name;
			this.value = value;
		}

		@SuppressWarnings("unused")
		Hello() {

		}

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}

		@SuppressWarnings("unused")
		public String getValue() {
			return value;
		}

		@SuppressWarnings("unused")
		public void setValue(String value) {
			this.value = value;
		}

	}

}
