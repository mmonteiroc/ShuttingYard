import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {
    @Test
    public void tests() {
        String expr;
        Token[] expected;

        expr = "1+311";
        expected = new Token[]{Token.tokNumber(1), Token.tokOp('+'), Token.tokNumber(311)};
        assertArrayEquals(expected, Token.getTokens(expr));

        expr = "((4/5)-6*(36+7))";
        expected = new Token[]{
                Token.tokParen('('), Token.tokParen('('), Token.tokNumber(4), Token.tokOp('/'),
                Token.tokNumber(5), Token.tokParen(')'), Token.tokOp('-'), Token.tokNumber(6),
                Token.tokOp('*'), Token.tokParen('('), Token.tokNumber(36), Token.tokOp('+'),
                Token.tokNumber(7), Token.tokParen(')'), Token.tokParen(')')
        };
        assertArrayEquals(expected, Token.getTokens(expr));
    }
}
