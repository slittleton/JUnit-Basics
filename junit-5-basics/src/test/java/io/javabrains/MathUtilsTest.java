package io.javabrains;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.Assumptions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When Running MathUtils")
class MathUtilsTest {

	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll // Must be static because it runs before the class is initialized
	static void beforeAllInit() {
		System.out.print("This needs to run before all");
	}

	@BeforeEach
	void init() {
		this.testInfo= testInfo;
		this.testReporter = testReporter;
		
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running " + testInfo.getDisplayName());
	}

	@AfterEach
	void cleanup() {
		System.out.print("Cleaning up...");
	}

	@Test
	@DisplayName("This is Testing The Add Method")
	@RepeatedTest(3)
	void testAdd() {

		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual);
	}

	@Test
	@Tag("Math")
	void testDivide() {
		boolean isServerUp = true;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
	}

	@Test
	@Tag("Math")
	@DisplayName("Multiply Method")
	void testMultiply() {
//		assertEquals(4, mathUtils.multiply(2, 2));
		
		
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2, 2)),
				() -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1))
				);
	}

	@Nested
	class addTest {
		@Test
		@DisplayName("Testing add method for positive nums")
		void testAddPositive() {
			int expected = 2;
			int actual = mathUtils.add(1, 1);
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("Testing add method for negative nums")
		void testAddNegative() {
			int expected = -2;
			int actual = mathUtils.add(-1, -1);
			assertEquals(expected, actual);
		}
	}

	@Test
	@Disabled // this annotation skips this test
	@DisplayName("TDD method. should not run")
	void testDiabled() {
		fail("This test should be disabled");
	}

//	@Test
//	void testComputeCircleRadius() {
//		MathUtils mathUtils = new MathUtils();
//		assertEquals(314.159265359, mathUtils.computeCircleArea(10), "Should return circle area");
//	}

}
