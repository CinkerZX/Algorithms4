import java.util.ArrayList;

class ResizingArrayDeque<E>{

    ResizeArrayQueueOfStrings<E> myDeque;

    public ResizingArrayDeque(){
        myDeque = new ResizeArrayQueueOfStrings(3);
    }

    public boolean isEmpty() {
        if (myDeque.getLength() == 0){return true;}
        else{return false;}
    }

    public int size(){
        //TODO: return the num of items in the deque
        return myDeque.size();
    }

    public void pushLeft(E item){
        //TODO: add an item to the left end
        myDeque.add(item);
    }

    public void pushRight(E item){
        //TODO: add an item to the right end
        myDeque.add_inverse(item);
    }

    public E popLeft(){
        //TODO: remove an item from the left end
        return myDeque.remove();
    }

    public E popRight(){
        //TODO: remove an item from the right end
        return myDeque.remove_inverse();
    }

    public static void main(String[] args) {
        ResizingArrayDeque myResizingArrayDeque = new ResizingArrayDeque();
        //Test isEmpty
        System.out.println("After initializing, is the deque empty? " + myResizingArrayDeque.isEmpty()); // True
        //Test size
        System.out.println("After initializing, the size of the deque is " + myResizingArrayDeque.size()); //0

        //Test pushLeft
        myResizingArrayDeque.pushLeft("Xin ");
        myResizingArrayDeque.pushLeft("is ");
        myResizingArrayDeque.pushLeft("a "); // a->is->Xin
        System.out.println(myResizingArrayDeque.myDeque.getLength()); //3

        //Test push right
        myResizingArrayDeque.pushRight("PhD");// a->is->Xin->PhD
        System.out.println(myResizingArrayDeque.myDeque.getLength()); //4

        //Test pop left
        System.out.println("Pop from the left: " + myResizingArrayDeque.popLeft()); //a
        System.out.println(myResizingArrayDeque.myDeque.getLength()); //3

        //Test pop right
        System.out.println("Pop from the right: " + myResizingArrayDeque.popRight()); //PhD
        System.out.println(myResizingArrayDeque.myDeque.getLength()); //2
    }

}
