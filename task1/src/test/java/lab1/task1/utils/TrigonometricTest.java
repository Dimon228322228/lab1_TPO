package lab1.task1.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrigonometricTest {

  @ParameterizedTest(name = "sec({0})")
  @DisplayName("Check corner values")
  @ValueSource(doubles = {
    -1.5707963267948966,  // -π/2 (undefined)
    -1.5700,              // Close to -π/2
    -1.0,
    -0.99,
    -0.5,
    -0.000001,
    -0.0001,
    -0.0,
    0.0,
    0.0001,
    0.000001,
    0.5,
    0.99,
    1.0,
    1.5700,               // Close to π/2
    1.5707963267948966,   // π/2 (undefined)
    Double.NaN,
    Double.POSITIVE_INFINITY,
    Double.MIN_VALUE,
  })
  void checkCornerDots(double param) {
    // For sec(x), we expect NaN for values where cos(x) = 0 (±π/2 + kπ)
    // and for other values we compare with Math.sec
    if (Math.abs(Math.cos(param)) < 1e-10) {
      // Should return NaN for undefined points
      assertEquals(Double.NaN, Trigonometric.sec(param), "Should return NaN for undefined points");
    } else {
      // Compare with Math.sec for valid inputs
      double expected = 1.0 / Math.cos(param);
      assertEquals(expected, Trigonometric.sec(param), 0.00001, "sec(" + param + ") should match Math.sec");
    }
  }

  @ParameterizedTest(name = "sec({0}) = {1}")
  @DisplayName("Check between dots (valid domain)")
  @ValueSource(doubles = {
    -1.4,
    -1.3,
    -1.2,
    -1.1,
    -1.0,
    -0.9,
    -0.8,
    -0.7,
    -0.6,
    -0.5,
    -0.4,
    -0.3,
    -0.2,
    -0.1,
    0.0,
    0.1,
    0.2,
    0.3,
    0.4,
    0.5,
    0.6,
    0.7,
    0.8,
    0.9,
    1.0,
    1.1,
    1.2,
    1.3,
    1.4,
    -999.9,
    999.9,
  })
  void checkBetweenDots(double x) {
    // Only test within valid domain (-π/2, π/2) excluding endpoints
    if (Math.abs(x) < Math.PI / 2) {
      double expected = 1.0 / Math.cos(x);
      assertAll(
        () -> assertEquals(expected, Trigonometric.sec(x), 0.0001, "sec(" + x + ") should match expected value")
      );
    }
  }

  @Test
  @DisplayName("Fuzzy testing")
  void checkRandomDots() {
    for (int i = 0; i < 1000; i++) {
      // Generate random values in valid domain (-π/2, π/2)
      double randomValue = ThreadLocalRandom.current().nextDouble(-Math.PI/2 + 0.0001, Math.PI/2 - 0.0001);
      double expected = 1.0 / Math.cos(randomValue);
      assertEquals(expected, Trigonometric.sec(randomValue), 0.0001, "Random test failed for x=" + randomValue);
    }
  }
}