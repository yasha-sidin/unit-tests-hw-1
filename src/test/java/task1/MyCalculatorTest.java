package task1;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyCalculatorTest {
    @Test
    public void testSum() {
        assertThat(Calculator.calculation(5, 5, '+')).isEqualTo(10);
    }

    @Test
    public void testMultiplication() {
        assertThat(Calculator.calculation(5, 5, '*')).isEqualTo(25);
    }

    @Test
    public void testDividing() {
        assertThat(Calculator.calculation(5, 5, '/')).isEqualTo(1);
    }

    @Test
    public void testSubtraction() {
        assertThat(Calculator.calculation(5, 5, '-')).isEqualTo(0);
    }

    @Test
    public void testDividingByZero() {
        assertThatThrownBy(() -> Calculator.calculation(5, 0, '/')).isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void testAnotherOperation() {
        assertThatThrownBy(() -> Calculator.calculation(7, 2, '.')).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testIntegerOverflowInAddition() {
        assertThatThrownBy(() -> Calculator.calculation(2_147_483_647, 1, '+')).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 0.0, 64.0, 32.0, 15.0, 19.0, 25.0, 4.0, 2.0})
    public void testSquareRootExtraction(double parameter) {
        assertThat(Calculator.squareRootExtraction(parameter)).isEqualTo(BigDecimal.valueOf(Math.sqrt(parameter)).setScale(10, RoundingMode.DOWN).doubleValue());
    }

    @Test
    public void testSquareRootExtractionFromNegativeNumber() {
        assertThatThrownBy(() -> Calculator.squareRootExtraction(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCalculatingDiscount() {
        assertThat(Calculator.calculatingDiscount(1000, 10)).isEqualTo(900.0);
    }

    @Test
    public void testCalculatingDiscountWithDiscountAmountWhichOverThenOneHundred() {
        assertThatThrownBy(() -> Calculator.calculatingDiscount(1000, 101)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void testCalculatingDiscountWithDiscountAmountWhichLessThenZero() {
        assertThatThrownBy(() -> Calculator.calculatingDiscount(1000, -1)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void testCalculatingDiscountWithPurchaseAmountWhichLessThanZero() {
        assertThatThrownBy(() -> Calculator.calculatingDiscount(-1, 90)).isInstanceOf(ArithmeticException.class);
    }


}
