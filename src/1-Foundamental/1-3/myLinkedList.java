import java.util.LinkedList;

public class myLinkedList<E> extends LinkedList<E> { // Create myLinkedList class, function: delete the kth element if exists
//    private LinkedList<myNode> myLinkedList;
    private myNode<E> head;

    public myNode getHead(){
        return head;
    }

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

    public static boolean find(myLinkedList list, String s){
        myNode nd = list.getHead().getNext();
        while(nd.getData() != null){
            if(nd.getData().equals(s)){ // find
                return true;
            }
            else{nd = nd.getNext();
                if (nd == null){return false;} // cannot find
            }
        }
        return false;
    }

    public myNode find(myNode h, myNode nd){
        myNode temp = h.getNext();
        while(temp.getData()!=null){
            if (equal(temp, nd)){ // find the nd, delete the rest
                return temp;
            }
            temp = temp.getNext();
            if (temp == null){break;}
        }
        return null;
    }

    public static myNode findIfNext(myNode h, String e){
        myNode<String> temp = h.getNext();
        while(temp.getData()!=null){
            if (temp.getNext().getData().equals(e)){ // find the nd, delete the rest
                return temp;
            }
            temp = temp.getNext();
            if (temp == null){break;}
        }
        return null;
    }

    public static boolean equal(myNode node, myNode aimNode){
        if(node.getData().equals(aimNode.getData())){ // find
            return true;
        }
        return false;
    }

    public void removeAfter(myNode nd){
        myNode temp = find(head,nd);
        if (temp != null){
            temp.setNext(null);
        }
    }

    public void insertAfter(myNode nodeA, myNode nodeB){
        myNode temp = find(head, nodeA);
        if (temp != null){
            myNode nodeC = temp.getNext();
            temp.setNext(nodeB);
            nodeB.setNext(nodeC);
        }
    }

    public static void remove(myLinkedList list, String s){
        //TODO remove the node after nodeA, which has the key s
        while(find(list,s)){
            myNode<String> nodeA = findIfNext(list.head, s);
            myNode<String> nodeB = nodeA.getNext();// contains key s
            myNode<String> nodeC = nodeB.getNext();
            if (nodeC!= null){
                nodeA.setNext(nodeC);
            }
        }
    }

    public static int max(myNode h){
        //TODO: return the value of the maxium key in the list, input is the head of the list
        int result = 0;
        h = h.getNext(); // the first node after head
        return maxHelper(h, result);
    }

    public static int maxHelper(myNode newNode, int result){
        while(newNode.getData()!=null){
            int temp = (int) newNode.getData();
            if (temp >result){result = temp;}
            newNode = newNode.getNext();
            if (newNode == null){return result;}
            else return maxHelper(newNode, result);
        }
        return result;
    }

    public static myNode reserve(myNode first){
        //TODO: input argument is the head of one list, reserve the list and return the new head |head| -> |1| -> |2|
        if (first == null) return null; // Boundary case1: this node is null
        if (first.getNext() == null) return first; // Boundary case2: there is only one node in the LinkedList
        myNode second = first.getNext();
        myNode rest = reserve(second); // return the end of the original list  |2| -> |1| -> null
        second.setNext(first); // in each reversion, change the linked order |1| -> |head|
        first.setNext(null); // |head| -> null
        return rest;
    }

    //Helpers
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

    public static void printOutList(myNode h){
        // Input argument is the head node
        myNode pointer = h.getNext();
        while (pointer.getData() != null){
            System.out.println(pointer.getData());
            pointer= pointer.getNext();
            if (pointer == null){
                break;
            }
        }
    }

    public static void main(String[] args) {
        //Test delete
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
//
//        // Test find
//        myLinkedList<String> mylist2 = new myLinkedList<>();
//        mylist2.add("I");
//        mylist2.add("am");
//        mylist2.add("perfect");
//        mylist2.add("and");
//        mylist2.add("happy");
//        System.out.println(find(mylist2,"happy"));
//        System.out.println(find(mylist2,"Happy"));
//        System.out.println(find(mylist2,"I"));
//
//        // Test removeAfter
//        mylist2.printOutList();
//        myNode aimNd = new myNode("and");
//        mylist2.removeAfter(aimNd);
//        mylist2.printOutList();
//        myNode aimNd2 = new myNode("happy");
//        mylist2.removeAfter(aimNd2);
//        mylist2.printOutList();
//
//        // Test insertAfter
//        mylist2.insertAfter(aimNd,aimNd2);
//        mylist2.printOutList();
//        myNode aimNd3 = new myNode("haha");
//        mylist2.insertAfter(aimNd3,aimNd2);
//        mylist2.printOutList();
//
//        // Test remove
//        myLinkedList<String> mylist3 = new myLinkedList<>();
//        mylist3.add("Today");
//        mylist3.add("is");
//        mylist3.add("a");
//        mylist3.add("beautiful");
//        mylist3.add("day");
//        mylist3.add("and");
//        mylist3.add("today");
//        mylist3.add("is");
//        mylist3.add("Saturday");
//        remove(mylist3,"is");
//        mylist3.printOutList();

        // Test max
        mylist.add(10);
        mylist.add(8);
        mylist.add(12);
        mylist.add(5);
        mylist.printOutList();
        System.out.println("The max integer in the list is: "+ max(mylist.head));

        //Test reverse
        printOutList(myLinkedList.reserve(mylist.head));
    }
}
