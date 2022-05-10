import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwoStackOneDequeTest {
    @Test
    public void TestEmpty(){
        TwoStacksOneDeque myTwoStacks = new TwoStacksOneDeque();
        assertTrue(myTwoStacks.empty("left"));
        assertTrue(myTwoStacks.empty("right"));

        myTwoStacks.push("left", "left-01");
        myTwoStacks.push("left", "left-02");
        assertFalse(myTwoStacks.empty("left"));
        assertTrue(myTwoStacks.empty("right"));

        myTwoStacks.push("right","right-01");
        myTwoStacks.push("right","right-02");
        assertFalse(myTwoStacks.empty("right"));
    }

    @Test
    public void TestPeek(){ // including pop and push
        TwoStacksOneDeque myTwoStacks = new TwoStacksOneDeque();
        Assert.assertEquals(null, myTwoStacks.peek("left"));
        Assert.assertEquals(null, myTwoStacks.peek("right"));

        myTwoStacks.push("left", "left-01");
        Assert.assertEquals("left-01", myTwoStacks.peek("left"));
        Assert.assertEquals(null, myTwoStacks.peek("right"));
        assertFalse(myTwoStacks.empty("left"));

        myTwoStacks.push("right", "right-01");
        Assert.assertEquals("right-01", myTwoStacks.peek("right"));
        assertFalse(myTwoStacks.empty("right"));
    }

    @Test
    public void TestSearch(){ // including pop and push
        TwoStacksOneDeque myTwoStacks = new TwoStacksOneDeque();
        Assert.assertEquals(0,myTwoStacks.search("left","left-01"));
        Assert.assertEquals(0,myTwoStacks.search("right","right-01"));

        myTwoStacks.push("left", "left-01");
        Assert.assertEquals(1,myTwoStacks.search("left","left-01"));
        Assert.assertEquals(0,myTwoStacks.search("right","right-01"));

        myTwoStacks.push("left", "left-02");
        Assert.assertEquals(2,myTwoStacks.search("left","left-01"));
        Assert.assertEquals(1,myTwoStacks.search("left","left-02"));
        Assert.assertEquals(0,myTwoStacks.search("right","right-01"));

        myTwoStacks.push("right", "right-01");
        Assert.assertEquals(1,myTwoStacks.search("right","right-01"));
    }

}
