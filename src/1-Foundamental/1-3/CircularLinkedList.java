import java.util.LinkedList;

public class CircularLinkedList<E> {
    myNode<E> last;

    public CircularLinkedList(){
        last = (myNode<E>) new myNode<String>("first");
        last.setNext(last);
    }

    public boolean isEmpty(){
        if (last.getNext().getData().equals("first")){
            return true;
        }
        return false;
    }

    public boolean add(E e){
        //TODO: insert that node before node "last"
        myNode node = new myNode(e);
        if (isEmpty()){last.setNext(node); node.setNext(last);return true;}
        goToEnd(last).setNext(node);
        node.setNext(last);
        return true;
    }

    public myNode goToEnd(myNode node){
        //TODO: return the node before the "last"
        if (isEmpty()){return new myNode(null);}
        myNode pointer = node;
        while(!pointer.getNext().getData().equals("first")){
            pointer = pointer.getNext();
        }
        return pointer;
    }

    public myNode goToEnd(){
        //TODO: return the node before the "last"
        if (isEmpty()){return new myNode(null);}
        myNode pointer = this.last;
        while(!pointer.getNext().getData().equals("first")){
            pointer = pointer.getNext();
        }
        return pointer;
    }

    public void remove(myNode node){
        //TODO: remove "node" from the linkedlist
        if (!node.equals(null)){
            myNode pointer = this.last;
            while(!pointer.getNext().getData().equals(node.getData())){
                pointer = pointer.getNext();
            }
            pointer.setNext(pointer.getNext().getNext());
        }
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
