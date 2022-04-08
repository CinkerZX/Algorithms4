import java.util.LinkedList;

public class myLinkedList<E> extends LinkedList<E> { // Create myLinkedList class, function: delete the kth element if exists
//    private LinkedList<myNode> myLinkedList;
    private myNode<E> head;

    public myLinkedList(){
        head = new myNode(null);
    }

    public boolean add(E e){
        //TODO: Appends the specified element to the end of this list
        myNode node = new myNode(e);
        goToEnd(head).setNext(node);
        return true;
    }

    public myNode goToEnd(myNode current){
        myNode pointer = current;
        while(pointer.getNext()!= null){
            pointer = pointer.getNext();
        }
        return pointer;
    }

    public void delete(int k) {
        //TODO: delete the kth element in a linked list, if exist
        if (k == 0){
            System.out.println("The argument has to be a natural integer.");
        }
        int i = 1;
        myNode pointer = head.getNext();
        while(pointer.getData()!=null && i < k-1){
            i++;
            pointer = pointer.getNext();
            if (pointer == null){break;}
        }
        if (i == k-1){
            myNode NodeK = pointer.getNext();
            if (NodeK.getData()!= null){
                pointer.setNext(NodeK.getNext());
            }
        }
    }

    public void printOutList(){
        myNode pointer = head.getNext();
        while (pointer.getData() != null){
            System.out.println(pointer.getData());
            pointer= pointer.getNext();
            if (pointer == null){
                break;
            }
        }
    }

    public static void main(String[] args) {
        myLinkedList<Integer> mylist = new myLinkedList<>();
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        mylist.add(4);
        mylist.add(5);
        mylist.delete(3);
        System.out.println("After deleting the 3th element: ");
        mylist.printOutList();
        mylist.delete(10);
        System.out.println("After deleting the 10th element: ");
        mylist.printOutList();
        mylist.delete(3);
        System.out.println("After deleting the 3 ed element: ");
        mylist.printOutList();
    }

}
