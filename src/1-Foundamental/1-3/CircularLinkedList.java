import java.util.LinkedList;

public class CircularLinkedList<E> extends myLinkedList<E> {
    private myNode<E> last;

    public CircularLinkedList(){
        last = (myNode<E>) new myNode<String>("first");
        last.setNext(last);
    }

    public boolean add(E e){
        //TODO: insert that node before node "last"
        myNode node = new myNode(e);
        goToEnd(last).setNext(node);
        node.setNext(last);
        return true;
    }

    public myNode goToEnd(myNode node){
        //TODO: return the node before the "last"
        myNode pointer = node;
        while(!pointer.getNext().getData().equals("first")){
            pointer = pointer.getNext();
        }
        return pointer;
    }

    //Helpers
    public void printOutList(){
        myNode pointer = last.getNext();
        while (!pointer.getData().equals("first")){
            System.out.println(pointer.getData());
            pointer= pointer.getNext();
        }
    }

    public static void main(String[] args) {
        // Test add
        CircularLinkedList<String> myCirList = new CircularLinkedList<>();
        myCirList.add("I");
        myCirList.add("am");
        myCirList.add("perfect");
        myCirList.add("and");
        myCirList.add("happy");
        myCirList.printOutList();
    }
}
