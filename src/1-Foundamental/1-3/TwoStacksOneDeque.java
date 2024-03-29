//import jdk.nashorn.internal.runtime.regexp.joni.constants.StackType;

import java.util.Stack;

public class TwoStacksOneDeque<Item> extends Deque<Item> {
    Deque<Item> myDeque;
    myDoubleDirectNode bottom;

    public TwoStacksOneDeque(){
        bottom = new myDoubleDirectNode<>((Item) "bottom");
        myDeque = new Deque(bottom);
    }

    /**
     * Check if both of the stacks are empty
     * @return
     */
    public boolean empty(String s){
        if (s == "left"){
            return myDeque.leftHead.getNext().equals(bottom);
        }
        return myDeque.rightHead.getBefore().equals(bottom);
    }

    public boolean empty(){
        return myDeque.leftHead.getNext().equals(bottom) && myDeque.rightHead.getBefore().equals(bottom);
    }

    public Item peek(String s) {
        Item result;
        if (empty()){
            return null;
        }
        if (s == "left"){
            if (empty("left")){
                return null;
            }
            result = myDeque.popLeft();
            myDeque.pushLeft(result);
        }else{
            if (empty("right")){
                return null;
            }
            result = myDeque.popRight();
            myDeque.pushRight(result);
        }
        return result;
    }

    public Item pop(String s){
        Item result;
        if (s == "left"){
            return myDeque.popLeft();
        }
        return myDeque.popRight();
    }

    public Item push(String s, Item item){
        if (s == "left"){
            myDeque.pushLeft(item);
        }
        else{
            myDeque.pushRight(item);
        }
        return item;
    }

    public int search(String s, Item item){
        int result = 0;
        myDoubleDirectNode pointer;
        if (s == "left"){
            pointer = myDeque.leftHead.getNext();
        }
        else{
            pointer = myDeque.rightHead.getBefore();
        }
        return searchHelper(pointer,item,result,s);
    }

    public int searchHelper(myDoubleDirectNode pointer, Item item, int cur, String s){
        Item obj;
        if (pointer.equals(bottom)){return 0;}
        else{
            obj = (Item) pointer.getData();
            cur++;
            if (obj == item){return cur;}
            else{
                if (s == "left"){
                    return searchHelper(pointer.getNext(), item, cur, s);
                }
                else{
                    return searchHelper(pointer.getBefore(), item, cur,s);
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
