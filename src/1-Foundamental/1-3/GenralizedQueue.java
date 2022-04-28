public interface GenralizedQueue<Item> {

    public boolean isEmpty();
    public boolean insert(Item x);
    public Item delete(int k);
    
}
