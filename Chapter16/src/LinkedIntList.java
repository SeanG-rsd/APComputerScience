public class LinkedIntList<E>
{
    private ListNode<E> front;

    public LinkedIntList()
    {
        front = null;
    }

    public int size()
    {
        int count = 0;
        ListNode<E> current = front;
        while (current != null)
        {
            current = current.next;
            count++;
        }
        return count;
    }

    public int indexOf(E value)
    {
        int index = 0;
        ListNode<E> current = front;
        while (current != null)
        {
            if (current.data.equals(value))
            {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    public void add(E value)
    {
        if (front == null)
        {
            front = new ListNode<E>(value);
        }
        else
        {
            ListNode<E> current = front;
            while (current != null)
            {
                current = current.next;
            }
            current = new ListNode<E>(value);
        }
    }

    public void add(E value, int index)
    {
        if (index == 0)
        {
            front = new ListNode<E>(value, front);
        }
        else
        {
            ListNode<E> current = nodeAt(index - 1);
            current.next = new ListNode<E>(value, current.next);
        }
    }

    public void remove(int index)
    {
        if (index == 0)
        {
            front = front.next;
        }
        else
        {
            ListNode<E> current = nodeAt(index - 1);
            current.next = current.next.next;
        }
    }

    private ListNode<E> nodeAt(int index)
    {
        ListNode<E> current = front;
        for (int i = 0; i < index; ++i)
        {
            current = current.next;
        }
        return current;
    }

    public E get(int index)
    {
        return nodeAt(index).data;
    }
    public String toString()
    {
        if (front == null)
        {
            return "[]";
        }
        else
        {
            String output = "[" + front.data;
            ListNode<E> current = front.next;
            while (current != null)
            {
                output += ", " + current.data;
                current = current.next;
            }
            output += "]";
            return output;
        }
    }
}
