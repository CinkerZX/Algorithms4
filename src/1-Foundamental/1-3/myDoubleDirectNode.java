public class myDoubleDirectNode<E> {
    private E data;
    private myDoubleDirectNode before;
    private myDoubleDirectNode next;

    public myDoubleDirectNode(E e){
        data = e;
        before = null;
        next = null;
    }

    public E getData() {
        return data;
    }

    public myDoubleDirectNode getNext() {
        return next;
    }

    public myDoubleDirectNode getBefore() {
        return before;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(myDoubleDirectNode next) {
        this.next = next;
    }

    public void setBefore(myDoubleDirectNode before) {
        this.before = before;
    }

    public boolean equals(myDoubleDirectNode node){
        return data==node.getData();
    }
}
