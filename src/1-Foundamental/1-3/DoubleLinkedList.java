import java.util.LinkedList;
import java.util.Queue;

public class DoubleLinkedList<E> extends LinkedList<E> {
    private myNode<E> leftHead;
    private myNode<E> rightHead;

    public DoubleLinkedList(){
        leftHead = new myNode<>(null);
        rightHead = new myNode<>(null);
        leftHead.setNext(rightHead);
        rightHead.setNext(leftHead);
    }


}
