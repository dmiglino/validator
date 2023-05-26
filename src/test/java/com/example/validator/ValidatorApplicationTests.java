package com.example.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidatorApplicationTests {

	@Test
	void mainTest() {
		assertDoesNotThrow(() -> ValidatorApplication.main(new String[] {}));
	}

}
