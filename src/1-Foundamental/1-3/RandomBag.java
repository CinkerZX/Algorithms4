import edu.princeton.cs.algs4.Bag;

import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

class RandomBag<Item> extends Bag<Item> implements Iterable<Item> {

    Bag myBag;


    public RandomBag(){
        myBag = new Bag();
    }

    public boolean isEmpty(){
        return myBag.isEmpty();
    }

    public int size(){
        return myBag.size();
    }

    public void add(Item item){
        myBag.add(item);
    }

    public Iterator<Item> iterator(){
        Object[] BagToArray = new Object[myBag.size()];
        int i = 0;
        for (Object o : myBag) {
            BagToArray[i] = o;
            i++;
        }
        return new iterRandomBag(BagToArray);
    }

    public static void main(String[] args) {
        RandomBag bag = new RandomBag();
        bag.add("alpha");
        bag.add("gama");
        bag.add("theta");
        bag.add("1");
        for (Object s : bag) {
            System.out.println(s);
        }
    }
}

class iterRandomBag<Item> implements Iterator<Item>{
    Object[] randomOrder;
    int pointer = 0;

    public iterRandomBag(Object[] originalOrder) {
        Collections.shuffle(Arrays.asList(originalOrder));
        randomOrder = originalOrder;
    }

    @Override
    public boolean hasNext() {
        if (pointer < randomOrder.length){
            return true;
        }
        return false;
    }

    @Override
    public Item next() {
        int temPoint = pointer;
        pointer++;
        return (Item) randomOrder[temPoint];
    }
}
