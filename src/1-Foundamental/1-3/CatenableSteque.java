public class CatenableSteque<Item> implements Catenable<CatenableSteque>{
    CircularLinkedList mySteque;

    public CatenableSteque(){
        mySteque = new CircularLinkedList();
    }

    public CatenableSteque(CircularLinkedList s){
        mySteque = s;
    }

    public Item pop(){
        if (mySteque.isEmpty()){return null;}
        myNode result = mySteque.goToEnd();
        mySteque.remove(result);
        return (Item) result.getData();
    }

    public Item push(Item item){
        mySteque.add(item);
        return item;
    }

    /**
     * add the item into the bottom of the stack
     * @return
     */
    public Item enqueue(Item item){
        if (mySteque.isEmpty()){
            mySteque.add(item);
            return item;
        }
        myNode bottom = mySteque.last.getNext();  // 0
        myNode newNode = new myNode(item);
        newNode.setNext(bottom);
        mySteque.last.setNext(newNode);
        return item;
    }

    // This: 0->1->2, catenabelSteque: c->b->a
    // Result: 2->1->0->c->b->a
    @Override
    public CatenableSteque caten(CatenableSteque catenableSteque) {
        // both this and queue is empty
        if (mySteque.isEmpty() && catenableSteque.mySteque.isEmpty()){return new CatenableSteque();}
        if (mySteque.isEmpty()){return catenableSteque;}
        if (catenableSteque.mySteque.isEmpty()){return new CatenableSteque(mySteque);}

        myNode endOfThis = mySteque.last.getNext(); //2
        myNode endOfqueue = catenableSteque.mySteque.last.getNext(); //c
        myNode headOfqueue = catenableSteque.mySteque.goToEnd(); //a
        headOfqueue.setNext(endOfThis); // c->0
        mySteque.last.setNext(endOfqueue); // last1->a
        return new CatenableSteque(mySteque);
    }
}






