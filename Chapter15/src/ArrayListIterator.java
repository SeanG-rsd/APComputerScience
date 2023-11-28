import java.util.NoSuchElementException;

public class ArrayListIterator<E>
{
    private int position;
    private ArrayList list;

    private boolean removeOK;

    public ArrayListIterator(ArrayList list)
    {
        this.list = list;
        position = 0;
        removeOK = false;
    }

    public boolean hasNext()
    {
        return position < list.size();
    }

    public E next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        }
        removeOK = true;
        E result = (E) list.get(position);
        position++;
        return result;
    }

    public void remove()
    {
        if (!removeOK)
        {
            throw new IllegalStateException();
        }
        list.remove(position - 1);
        position--;
        removeOK = false;
    }
}
