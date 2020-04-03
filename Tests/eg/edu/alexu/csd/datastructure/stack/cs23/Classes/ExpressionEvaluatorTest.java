package eg.edu.alexu.csd.datastructure.stack.cs23.Classes;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.filter.ClasspathScanningSupport;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {
    ExpressionEvaluator testObj = new ExpressionEvaluator();
    int value;
    @Test
    void test() {

        // PostFix for alphanumeric expressions
        assertEquals("a b / c - d e * + a c * -",testObj.infixToPostfix("a / b - c + d * e - a * c"));
        assertEquals("a b c - d + / e a - * c *",testObj.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));

        value = testObj.evaluate( testObj.infixToPostfix("6*2  +  -- -- -5+0") );
        assertEquals(value, 7);
         value = testObj.evaluate( testObj.infixToPostfix("03+10*1*(1) + -05") );
        assertEquals(value, 8);
        value = testObj.evaluate( testObj.infixToPostfix("03+10*1*(1) + (-05)*(-1*-1)*-1") );
        assertEquals(value, 18);

        value = testObj.evaluate( testObj.infixToPostfix("03+10*1*(1) + -05*-1*-1*-1") );
        assertEquals(value, 18);


        value = testObj.evaluate( testObj.infixToPostfix(" (5*2) - (+6*1) - (-1*2) ") );
        assertEquals(value, 6);


        value = testObj.evaluate( testObj.infixToPostfix("+---++05 ") );
        assertEquals(value, -5);

         value = testObj.evaluate( testObj.infixToPostfix(" +1 * -+(+++2) ") );
        assertEquals(value, 2);


        // Single operands
        value = testObj.evaluate( testObj.infixToPostfix(" 1") );
        assertEquals(value, 1);

        value = testObj.evaluate( testObj.infixToPostfix(" -1") );
        assertEquals(value, -1);

        value = testObj.evaluate( testObj.infixToPostfix("+(+1)") );
        assertEquals(value, 1);


    }

    @Test
    void testValidation(){

        // testing parentheses
        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("(3 +) 5") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 + () + 5") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("(3 -)+ 5") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("++ (*-- 05) ") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("(+-)--++05  ") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3) *(2") );
        });


        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 * () 2") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 * 2(2) ") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 + (*2) ") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 + (2*) ") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 + (2-) ") );
        });


        // testing operators
        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("*3 * 2") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 ** 2") );
        });
        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("3 *+/ 2 ") );
        });

        // single operands
        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("*1") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("1/") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("()") );
        });

        assertThrows(RuntimeException.class, () -> {
            testObj.evaluate( testObj.infixToPostfix("+(+3)+") );
        });


        // special Character
        assertThrows(RuntimeException.class, () -> {
            testObj.infixToPostfix("a / b$ - c + d * #e - a * c");
        });

        // [ is not accepted
        assertThrows(RuntimeException.class, () -> {
            testObj.infixToPostfix("[a / b - c + d * ]e - a * c");
        });


    }

}