
public class myNode<E> {
    private E data;
    private myNode next;
    public myNode(E e){
        data = e;
        next = null;
    }

    public E getData() {
        return data;
    }

    public myNode getNext() {
        return next;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(myNode next) {
        this.next = next;
    }
}
