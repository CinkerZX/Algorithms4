import java.util.ArrayList;
import java.util.Random;

class RandomQueue<Item> extends ResizeArrayQueueOfStrings<Item>{
    /**
     * Create an empty random queue
     */
    public RandomQueue() {
        super();
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
//        Random random = new Random();
//        int N = this.getLength();
//        int randomPointer = random.nextInt(N); //0~N-1
//        Item picked = this.getItem(randomPointer);
//        this.setItem(randomPointer,this.peek()); // replace
//        this.setItem(0,picked);
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

    public static void main(String[] args) {
        RandomQueue rq = new RandomQueue();
        System.out.println(rq.isEmpty());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.isEmpty());
        System.out.println("Original queue");
        for (Object i:rq) {
            System.out.println(i);
        }
        System.out.println("************");
        System.out.println("sample");
        System.out.println(rq.sample());
        System.out.println("************");
        for (Object i:rq) {
            System.out.println(i);
        }
        System.out.println("************");
        System.out.println("dequeue");
        System.out.println(rq.dequeue());
        System.out.println("************");
        for (Object i:rq) {
            System.out.println(i);
        }
        System.out.println("************");
        System.out.println("sample");
        System.out.println(rq.sample());
        System.out.println("************");
        for (Object i:rq) {
            System.out.println(i);
        }
        System.out.println("************");
        System.out.println("dequeue");
        System.out.println(rq.dequeue());
        System.out.println("************");
        for (Object i:rq) {
            System.out.println(i);
        }
        System.out.println("************");
    }


}
