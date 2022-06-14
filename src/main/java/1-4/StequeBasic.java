public interface StequeBasic<Item> {
    // is the steque empty?
    boolean isEmpty();

    // return the num of items in the steque
    int size();

    // add an item to the top / begining
    void push(Item i);

    // add an item to the bottom / end
    void enqueue(Item i);

    //remove an item from the top / beginning
    Item pop();
}
