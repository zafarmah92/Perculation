import java.util.Iterator;
public class RandomizedQueue <Item> implements Iterable<Item>
{
    private Item[] s;
    private int N;
    private int capacity;
    //construct an empty randomized queue
    public RandomizedQueue()
    {
        N = 0;
        s = (Item[]) new Object[1];
    }
    
    //is the queue empty?
    public boolean isEmpty()
    {
        return N == 0;
    }
    //return the number of items on the queue
    public int size()
    {
        return N;
    }
    //add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }
    private void resize(int capacity)
    {
        if (capacity <= 0) 
            capacity = 1;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }
    //delete and return a random item
    public Item dequeue()
    {
        if (N == 0)
            throw new java.util.NoSuchElementException();
        int randomNumber = StdRandom.uniform(N);
        exch(s, randomNumber, N-1);
        Item x = s[--N];
        s[N] = null;
        if (N <= s.length/4)
            resize(s.length/2);
        return x;
    }
    private void exch(Item[] a, int i, int j)
    {
        Item swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        
    }
    //return (but not delete) a random item
    public Item sample()
    {
        if (N == 0)
            throw new java.util.NoSuchElementException();
        int randomNumber = StdRandom.uniform(N);
        return s[randomNumber];
        
    }
    //return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int i = N;
        private int[] idx;
        public RandomizedQueueIterator()
        {
            idx = new int[N];
            for (int j = 0; j < i; j++)
            {
                idx[j] = j;
            }
            StdRandom.shuffle(idx);
        }
        public boolean hasNext()
        {
            return i > 0;
        }
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return s[idx[--i]];
        }
    } 
    //gamingumar is here!
}