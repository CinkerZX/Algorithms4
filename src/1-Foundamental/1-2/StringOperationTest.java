import org.junit.Test;

import static org.junit.Assert.*;

public class StringOperationTest {
    @Test
    public void TestifCircularRotate(){
        String s1 = "ABCDE";
        String s2 = "CDEFG";
        String s3 = "CDEAB";
        String s4 = "CDE";
        assertFalse(StringOperation.ifCircularRotate(s1,s2));
        assertTrue(StringOperation.ifCircularRotate(s1,s3));
        assertTrue(StringOperation.ifCircularRotate(s3,s1));
        assertFalse(StringOperation.ifCircularRotate(s4,s3));
    }
}
