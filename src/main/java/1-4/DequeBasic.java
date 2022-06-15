import java.util.Iterator;

public interface DequeBasic<Item> {
    public void addFirst(Item item);

    public void addLast(Item item);

    public boolean offerFirst(Item item);

    public boolean offerLast(Item item);

    public Item removeFirst();

    public Item removeLast();

    public Item pollFirst();

    public Item pollLast();

    public Item getFirst();

    public Item getLast();

    public Item peekFirst();

    public Item peekLast();

    public boolean removeFirstOccurrence(Object o);

    public boolean removeLastOccurrence(Object o);

    public boolean add(Item item);

    public boolean offer(Item item);

    public Item remove();

    public Item poll();

    public Item element();

    public Item peek();

    public void push(Item item);

    public Item pop();

    public boolean remove(Object o);

    public boolean contains(Object o);

    public int size();

    public Iterator<Item> iterator();

    public Iterator<Item> descendingIterator();
}
