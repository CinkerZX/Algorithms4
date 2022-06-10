public interface QueueBasic<Item> {
    public boolean add(Item item);

    public boolean offer(Item item);

    public Item remove();

    public Item poll();

    public Item element();

    public Item peek();
}
