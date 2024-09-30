import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    // Test for addition
    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-1, 1));
    }

    // Test for subtraction
    @Test
    public void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
        assertEquals(-5, calculator.subtract(5, 10));
    }

    // Test for multiplication
    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(5, 0));
    }

    // Test for division
    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    // Test for division by zero (expecting ArithmeticException)
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }
}
