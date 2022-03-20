import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParenthesesTest {
    @Test
    public void TestParenthesesTest(){
        String s1 = "{}}";
        String s2 = "[{]}";
        String s3 = "[as{ds]fda}";
        String s4 = "[][s]{}(asdf)";
        String s5 = "{[(fadsf)]}[][]";

        assertFalse(Parentheses.checkParenthese(s1));
        assertFalse(Parentheses.checkParenthese(s2));
        assertFalse(Parentheses.checkParenthese(s3));
        assertTrue(Parentheses.checkParenthese(s4));
        assertTrue(Parentheses.checkParenthese(s5));

    }
}
