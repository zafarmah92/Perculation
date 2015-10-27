import java.util.Iterator;
public class Deque<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int sizeOfDeque;
    //construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
        sizeOfDeque = 0;
    }
    
    private class Node
    {
        private Item item;
        private Node next;
        private Node previous;
    }
    //is the deque empty
    public boolean isEmpty()
    {
        return first == null;
    }
    //return the number of items on the deque
    public int size()
    {
        return sizeOfDeque;
    }
    //insert the item at the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (isEmpty())
        {
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
        }
        else
        {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.previous = null;
            oldFirst.previous = first;
        }
        sizeOfDeque++;
    }
    //insert the item at the end
    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (isEmpty())
        {
            last = new Node();
            last.item = item;
            last.previous = null;
            last.next = null;
            first = last;
        }
        else
        {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.previous = oldLast;
            last.next = null;
            oldLast.next = last;
        }
        sizeOfDeque++;
        
    }
    //delete and return the item at the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        Node n = first;
        if (size() == 1)
        {
            first = null;
            last = null;
        }
        else
        {
            first = first.next;
            first.previous = null;
        }
        sizeOfDeque--;
        n = null;
        return item;
    }
    //delete and return the item at the end
    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        Node n = last;
        if (size() == 1)
        {
            first = null;
            last = null;
        }
        else
        {
            last = last.previous;
            last.next = null;
        }
        sizeOfDeque--;
        n = null;
        return item;
    }
    //return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext()
        {
            return current != null;
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next()
        {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    //gamingumar is here!
}