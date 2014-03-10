import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
	
	private int size;
	private Node<Item> first;
	private Node<Item> last;
	
	private class Node<Item> {
		public Node<Item> previous;
		public Node<Item> next;
		public Item item;
		
        public Node(Node<Item> previous, Node<Item> next, Item item)
        {
            this.previous = previous;
            this.next = next;
            this.item = item;
        }
	}
	
	private class DequeIterator implements Iterator<Item>
	{
		private Node<Item> current = first;
		
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
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			
			Item item = current.item;
			current = current.next;
			
			return item;
		}
	}
	
   
	public Deque()                           // construct an empty deque
	{
		size = 0;
		first = null;
		last = null;
	}
   
	public boolean isEmpty()                 // is the deque empty?
	{
		return size == 0;
	}
   
	public int size()                        // return the number of items on the deque
	{
		return size;
	}
	
	public void addFirst(Item item)          // insert the item at the front
	{
		if (item == null)
			throw new java.lang.NullPointerException();
		Node<Item> temp = new Node<Item>(null,first,item);
		if (!isEmpty())
		{
			first.previous = temp;
		}
		
		first = temp;
		
		if (last == null) last = first;
		size++;
		
	}
	
	public void addLast(Item item)           // insert the item at the end
	{
		if (item == null)
			throw new java.lang.NullPointerException();
		
		Node<Item> temp = new Node<Item>(last,null,item);
		if (last != null)
		{
			last.next = temp;
		}
		
		last = temp;
		
		if (isEmpty()) first = last;
		size++;
		
	}
   
	public Item removeFirst()                // delete and return the item at the front
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Item temp = first.item;
		
		if (first.next != null)
		{
			first.next.previous = null;
		}
		if (first == last)
		{
			
			last = null;
			
		}
		first = first.next;
		size --;
		return temp;
	}
	public Item removeLast()                 // delete and return the item at the end
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Item temp = last.item;
		
		if (last.previous != null)
		{
			last.previous.next = null;
		}
		if (first == last)
		{
			first = null;
			last = null;
		}
		// No other options
		if (last != null)
			last = last.previous;
		
		
		size --;
		return temp;
	}
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
	   return new DequeIterator();
   }

}