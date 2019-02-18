import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EvaluatorTest {
    @Test
    public void tests_rpn() {
        Token[] tokens;

        tokens = new Token[]{Token.tokNumber(3), Token.tokNumber(5), Token.tokOp('+')};
        assertEquals(8, Evaluator.calcRPN(tokens));


        tokens = Token.getTokens("40 6 -");
        assertEquals(34, Evaluator.calcRPN(tokens));

        tokens = Token.getTokens("69 32 +");
        assertEquals(101, Evaluator.calcRPN(tokens));

        tokens = Token.getTokens("3 1 2 + *");
        assertEquals(9, Evaluator.calcRPN(tokens));

        tokens = Token.getTokens("5 1 2 + 4 * + 3 -");
        assertEquals(14, Evaluator.calcRPN(tokens));

        tokens = Token.getTokens("1 2 20 5 - * +");
        assertEquals(31, Evaluator.calcRPN(tokens));


    }
    @Test
    public void tests_2_operands() {
        assertEquals(5, Evaluator.calculate("2+3"));
        assertEquals(14, Evaluator.calculate("2*7"));
        assertEquals(-15, Evaluator.calculate("46-61"));
        assertEquals(10, Evaluator.calculate("32/3"));
    }

    @Test
    public void tests_n_operands() {
        assertEquals(17, Evaluator.calculate("2+3+5+7"));
        assertEquals(-2, Evaluator.calculate("10-1-1-4-6"));
        assertEquals(2880, Evaluator.calculate("9*2*5*4*8"));
        assertEquals(8, Evaluator.calculate("100/2/6"));
    }


    @Test
    public void tests_sumes_restes_combinades() {
        assertEquals(3, Evaluator.calculate("1+1+1"));
        assertEquals(2, Evaluator.calculate("4+5-7"));
        assertEquals(4, Evaluator.calculate("6-8+9-3"));
        assertEquals(-18, Evaluator.calculate("6-8-9-3+67-3+8+9+9-94"));
    }

    @Test
    public void tests_operacions_precedencia() {
        assertEquals(14, Evaluator.calculate("4*2+6"));
        assertEquals(44, Evaluator.calculate("4*2+6*6"));
        assertEquals(-6, Evaluator.calculate("4+2*6-66/3"));
        assertEquals(152, Evaluator.calculate("78*4/3+87-7/3*7-8+5-23*1+4-65/10+6/2"));
    }

    @Test
    public void tests_parentesis() {
        assertEquals(7, Evaluator.calculate("(1+5)-(2-3)"));
        assertEquals(-6, Evaluator.calculate("(1+5)*(2-3)"));
        assertEquals(-518, Evaluator.calculate("6*5*(3-6)+60/(4-1)-(56*8)"));
        assertEquals(-48, Evaluator.calculate("((1+5)*4)/(3-4)*2"));
        assertEquals(-1, Evaluator.calculate("(((3-4)))"));
        assertEquals(-25, Evaluator.calculate("((5+34)*3-78+6)*1-(54+65/3+7-1000/(100-20))"));
    }
}
