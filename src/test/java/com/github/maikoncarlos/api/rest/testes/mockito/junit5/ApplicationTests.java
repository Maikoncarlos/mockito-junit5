package com.github.maikoncarlos.api.rest.testes.mockito.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	@DisplayName("Classe Principal")
	void main() {
		Application.main(new String[]{});
	}

}
