public interface StackBaisc<Item> {
    // Test if this tack is empty
    public boolean empty();

    // Look at the object at the top of the stack without moving
    public Item peek();

    // Remove the object at the top
    public Item pop();

    // Push an item onto the top
    public Item push(Item e);

    // Returns the 1-based position where an object is on this stack
    public int search(Object o);
}
