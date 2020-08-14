package br.com.az.config;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.az.service.TestService;

@SpringBootTest
class LearnJunitApplicationTests {

	TestService ts = new TestService();
	int[] fibonacciSequence = { 0, 1, 1, 2, 3, 5, 8, 13 };

	@Test
	void testSum() {
		assertEquals(20, ts.sum(10, 10));
	}

	@Test
	void testIsNull() {
		assertNull(ts.isNull());
	}

	@Test
	void testIsNotNull() {
		assertNotNull(ts.isNotNull());
	}

	@Test
	void testRoundingEven() {
		assertEquals(3, ts.roundingEven(new BigDecimal(10), new BigDecimal(3)));
	}

	@Test
	void testCeiling() {
		assertEquals(4, ts.ceiling(new BigDecimal(10), new BigDecimal(3)));
	}

	@Test
	void testContainsA() {
		assertTrue(ts.containsA("Java"));
	}

	@Test
	void testCountA() {
		assertEquals(2, ts.countA("Java"));
	}

	@Test
	void testFibonacciSequence() {
		assertArrayEquals(fibonacciSequence, ts.createFbSequence());
	}

	@Test
	void lambdaExpressions() {
		assertTrue(Stream.of(1, 2, 3).mapToInt(i -> i).sum() > 5, () -> "Sum should be greater than 5");
	}

	@Test
	void groupAssertions() {
		int[] numbers = { 0, 1, 2, 3, 4 };
		assertAll("numbers", () -> assertEquals(numbers[0], 0), () -> assertEquals(numbers[3], 3),
				() -> assertEquals(numbers[4], 4));
	}

	@Test
	void trueAssumption() {
		assumeTrue(5 > 1);
		assertEquals(5 + 2, 7);
	}

	@Test
	void falseAssumption() {
		assumeFalse(5 < 1);
		assertEquals(5 + 2, 7);
	}

	@Test
	void assumptionThat() {
		String someString = "Just a string";
		assumingThat(someString.equals("Just a string"), () -> assertEquals(2 + 2, 4));
	}

	@Test
	void shouldThrowException() {
		Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
			throw new UnsupportedOperationException("Not supported");
		});
		assertEquals(exception.getMessage(), "Not supported");
	}

	@Test
	void assertThrowsException() {
		String str = null;
		assertThrows(IllegalArgumentException.class, () -> {
			Integer.valueOf(str);
		});
	}

}
