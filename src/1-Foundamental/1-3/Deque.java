import java.util.LinkedList;

class Deque<E> extends LinkedList<E> {
    myDoubleDirectNode<E> leftHead;
    myDoubleDirectNode<E> rightHead;

    public Deque(){
        leftHead = new myDoubleDirectNode<>((E) "left");
        rightHead = new myDoubleDirectNode<>((E)"right");
        leftHead.setNext(rightHead);
        rightHead.setBefore(leftHead);
    }

    public Deque(myDoubleDirectNode bottom){
        leftHead = new myDoubleDirectNode<>((E) "left");
        rightHead = new myDoubleDirectNode<>((E)"right");
        leftHead.setNext(bottom);
        rightHead.setBefore(bottom);
    }

    @Override
    public boolean isEmpty() {
        if (leftHead.getNext() == rightHead){return true;}
        else{return false;}
    }

    public int size(){
        //TODO: return the num of items in the deque
        int numItem = 0;
        myDoubleDirectNode pointer = leftHead.getNext();
        while(pointer != rightHead){
            numItem++;
            pointer = pointer.getNext();
        }
        return numItem;
    }

    public void pushLeft(E item){
        //TODO: add an item to the left end
        myDoubleDirectNode newNode = new myDoubleDirectNode(item);
        myDoubleDirectNode temp = leftHead.getNext();
        leftHead.setNext(newNode);
        newNode.setBefore(leftHead);
        newNode.setNext(temp);
        temp.setBefore(newNode);
    }

    public void pushRight(E item){
        //TODO: add an item to the right end
        myDoubleDirectNode newNode = new myDoubleDirectNode(item);
        myDoubleDirectNode temp = rightHead.getBefore();
        rightHead.setBefore(newNode);
        newNode.setNext(rightHead);
        newNode.setBefore(temp);
        temp.setNext(newNode);
    }

    public E popLeft(){
        //TODO: remove an item from the left end
        E result;
        if (this.isEmpty()){
            result = null;
        }
        else{
            myDoubleDirectNode firstLeftNode = leftHead.getNext();
            result =(E) firstLeftNode.getData();
            leftHead.setNext(firstLeftNode.getNext());
            firstLeftNode.getNext().setBefore(leftHead);
        }
        return result;
    }

    public E popRight(){
        //TODO: remove an item from the right end
        E result;
        if (this.isEmpty()){
            result = null;
        }
        else{
            myDoubleDirectNode firstRightNode = rightHead.getBefore();
            result =(E) firstRightNode.getData();
            rightHead.setBefore(firstRightNode.getBefore());
            firstRightNode.getBefore().setNext(rightHead);
        }
        return result;
    }

    public static void main(String[] args) {
        Deque myDeque = new Deque();
        //Test isEmpty
        System.out.println("After initializing, is the deque empty? " + myDeque.isEmpty()); // True

        //Test size
        System.out.println("After initializing, the size of the deque is " + myDeque.size()); //0

        //Test pushLeft
        myDeque.pushLeft("Xin ");
        myDeque.pushLeft("is ");
        myDeque.pushLeft("a ");
        System.out.println("The current size of the deque is " + myDeque.size()); //3

        //Test push right
        myDeque.pushRight("PhD");
        System.out.println("The current size of the deque is " + myDeque.size()); //4

        //Test pop left
        System.out.println("Pop from the left: " + myDeque.popLeft()); //a
        System.out.println("The current size of the deque is " + myDeque.size()); //3

        //Test pop right
        System.out.println("Pop from the right: " + myDeque.popRight()); //PhD
        System.out.println("The current size of the deque is " + myDeque.size()); //2
    }
}
