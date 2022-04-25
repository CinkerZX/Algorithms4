import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class RandomQueue<Item> extends ResizeArrayQueueOfStrings<Item> implements Iterable<Item>{
    /**
     * Create an empty random queue
     */
    public RandomQueue() {
        super();
    }

    public RandomQueue(ArrayList<Item> arr){
        super();
        for (int i = 0; i < arr.size(); i++) {
            super.add(arr.get(i));
        }
    }

    /**
     * is the queue empty
     * @return true/false
     */
    public boolean isEmpty(){
        if (this.getLength()==0){return true;}
        return false;
    }

    /**
     * add an item
     * @param item
     */
    public void enqueue(Item item){
        this.add(item);
    }

    /**
     * remove and return a random item (sample without replacement
     */
    public Item dequeue(){
        Item result = this.sample();
        this.remove_inverse();
        return result;
    }

    /**
     * return a random item, but do not remove (sample with replacement
     */
    public Item sample(){
        Random random = new Random();
        int N = this.getLength();
        int randomPointer = random.nextInt(N); //0~N-1

        final Item picked = this.getItem(randomPointer);
        this.setItem(randomPointer,this.getItem(0)); // replace
        this.setItem(0,picked);
        return picked;
    }

    @Override
    public Iterator<Item> iterator(){ // Define my own iterator, necessary if want to use for each to print the result
        return new Iterator<Item>() {
            private ArrayList<Item> myArr = RandomQueue.super.getMyQueue();
            private RandomQueue<Item> rq = new RandomQueue(myArr);
            // need to build a new constructor for RandomQueue to copy the current RandomQueue object
            // cannot refer to the current RandomQueue object

            @Override // Override the original result
            public boolean hasNext(){
                return (!rq.isEmpty());
            }

            @Override
            public Item next(){
                rq.sample();
                return (Item) rq.dequeue();
            }
        };
    }

    public static void main(String[] args) {
        RandomQueue rq = new RandomQueue();
        System.out.println(rq.isEmpty());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        // test the iterator
        for (Object i : rq) {
            System.out.println(i);
        }
        System.out.println(rq.isEmpty());
//        System.out.println(rq.isEmpty());
//        System.out.println("Original queue");
//        for (Object i:rq) {
//            System.out.println(i);
//        }
//        System.out.println("************");
//        System.out.println("sample");
//        System.out.println(rq.sample());
//        System.out.println("************");
//        for (Object i:rq) {
//            System.out.println(i);
//        }
//        System.out.println("************");
//        System.out.println("dequeue");
//        System.out.println(rq.dequeue());
//        System.out.println("************");
//        for (Object i:rq) {
//            System.out.println(i);
//        }
//        System.out.println("************");
//        System.out.println("sample");
//        System.out.println(rq.sample());
//        System.out.println("************");
//        for (Object i:rq) {
//            System.out.println(i);
//        }
//        System.out.println("************");
//        System.out.println("dequeue");
//        System.out.println(rq.dequeue());
//        System.out.println("************");
//        for (Object i:rq) {
//            System.out.println(i);
//        }
//        System.out.println("************");
    }


}
