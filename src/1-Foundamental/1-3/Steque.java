import java.util.LinkedList;

public class Steque<E> extends myLinkedList<E> {
    private myNode<E> head;

    public myNode<E> getHead() {
        return head;
    }

    public Steque(){
        head = new myNode(null);
    }

    public void push(myNode node){
        //TODO: add this node at the end of the queue
        myNode lastNode = goToEnd(head);
        lastNode.setNext(node);
    }

    public void push(E e){
        //TODO: add this node at the end of the queue
        myNode insertNode = new myNode(e);
        push(insertNode);
    }

    public void enqueue(myNode node){
        //TODO: add this node after the head of the queueu
        myNode temp = head.getNext();
        head.setNext(node);
        node.setNext(temp);
    }

    public void enqueue(E e){
        //TODO: add this node after the head of the queueu
        myNode insertNode = new myNode(e);
        enqueue(insertNode);
    }

    public E pop(){
        //TODO: pop the node at the end of this queue
        myNode result = goToEnd(head);
        goToPenultimate(head).setNext(null);
        return (E)result.getData();
    }

    public myNode goToPenultimate(myNode h){
        myNode pointer = h;
        while(pointer.getNext().getNext()!= null){
            pointer = pointer.getNext();
        }
        return pointer;
    }

    public static void main(String[] args) {
        Steque<String> mySteque = new Steque<>();
        mySteque.push("First");
        mySteque.push("Second");
        myNode node3 = new myNode("Third");
        mySteque.push(node3);
        printOutList(mySteque.getHead());//First->second->Third
        mySteque.enqueue("Zero");
        printOutList(mySteque.getHead());//Zero->First->second->Third
        mySteque.pop();
        printOutList(mySteque.getHead());//Zero->First->second
    }
}
