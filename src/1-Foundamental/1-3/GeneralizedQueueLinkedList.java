import java.util.Iterator;
import java.util.LinkedList;

class GeneralizedQueueLinkedList<Item> extends LinkedList<Item> implements GenralizedQueue<Item>, Iterable<Item> {
    private LinkedList<Item> myLinkedList;

    public LinkedList<Item> copyMyLinkedList(){
        LinkedList<Item> result = new LinkedList<>();
        for (Item i:myLinkedList) {
            result.add(i);
        }
        return result;
    }

    public GeneralizedQueueLinkedList(){
        myLinkedList = new myLinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return myLinkedList.isEmpty();
    }

    @Override
    public boolean insert(Item x) {
        myLinkedList.addFirst(x);
        return false;
    }

    @Override
    public Item delete(int k) {
        Item result = myLinkedList.get(k-1);
        myLinkedList.remove(k-1);
        return result;
    }

    @Override
    public Iterator<Item> iterator(){
        LinkedList<Item> ll = this.copyMyLinkedList();

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                if (ll.size()==0){return false;}
                else{return true;}
            }

            @Override
            public Item next() {
                Item result =(Item) ll.get(ll.size()-1);
                ll.remove(ll.size()-1);
                return result;
            }
        };
    }

    public static void main(String[] args) {
        GeneralizedQueueLinkedList myQLL = new GeneralizedQueueLinkedList();
        System.out.println(myQLL.isEmpty());
        myQLL.insert(0);
        myQLL.insert(1);
        myQLL.insert(2);
        myQLL.insert(3);
        System.out.println(myQLL.isEmpty());
        System.out.println(myQLL.delete(3)); // 1
        for (Object o:myQLL) {
            System.out.println(o);
        }
        System.out.println(myQLL.isEmpty());
    }
}
