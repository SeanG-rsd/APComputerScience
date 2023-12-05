import java.util.List;

public class ListNode<E>
{
    public E data;
    public ListNode<E> next;

    public ListNode(E value)
    {
        this(value, null);
    }

    public ListNode(E value, ListNode<E> next)
    {
        data = value;
        this.next = next;
    }
}
