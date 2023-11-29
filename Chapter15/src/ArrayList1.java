import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList1<E>
{

    private class ArrayListIterator implements Iterator<E>
    {
        private int position;

        private boolean removeOK;

        public ArrayListIterator()
        {
            position = 0;
            removeOK = false;
        }

        public boolean hasNext()
        {
            return position < size();
        }

        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            removeOK = true;
            E result = get(position);
            position++;
            return result;
        }

        public void remove()
        {
            if (!removeOK)
            {
                throw new IllegalStateException();
            }
            ArrayList1.this.remove(position - 1);
            position--;
            removeOK = false;
        }
    }
    private E[] elementData;
    private int size;

    public static final int DEFAULT_CAPACITY = 100;

    public ArrayList1()
    {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList1(int capacity)
    {
        checkCapacity(capacity);
        elementData = (E[]) new Object[capacity];
        size = 0;
    }

    public void add(E value)
    {
        checkCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    public void add(E value, int index)
    {
        checkCapacity(size + 1);
        for (int i = size; i >= index + 1; i--)
        {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    public void print()
    {
        if (size == 0)
        {
            System.out.println("[]");
        }
        else
        {
            System.out.print("[" + elementData[0]);
            for (int i = 1; i < size; ++i)
            {
                System.out.print(", " + elementData[i]);
            }
            System.out.println("]");
        }
    }

    public int size()
    {
        return size;
    }

    public E get(int index)
    {
        checkIndex(index);
        return elementData[index];
    }

    public int indexOf(E value)
    {
        for (int i = 0; i < size; ++i)
        {
            if (elementData[i].equals(value))
            {
                return i;
            }
        }
        return -1;
    }

    public void remove(int index)
    {
        checkIndex(index);
        for (int i = index; i < size - 1; ++i)
        {
            elementData[index] = elementData[index + 1];
        }
        elementData[index - 1] = null;
        size--;
    }

    public String toString()
    {
        if (size == 0)
        {
            return "[]";
        }
        else
        {
            String output = "[" + elementData[0];
            for (int i = 1; i < size; ++i)
            {
                output += ", " + elementData[i];
            }
            output += "]";
            return output;
        }

    }

    private void checkCapacity(int capacity)
    {
        if (capacity > elementData.length)
        {
            throw new IllegalStateException("exceeds list capacity");
        }
    }

    private void checkIndex(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("index : " + index);
        }
    }

    public boolean contains(E value)
    {
        return indexOf(value) >= 0;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void set(int index, E value)
    {
        checkIndex(index);
        elementData[index] = value;
    }

    public void clear()
    {
        for (int i =0 ;i < size; ++i)
        {
            elementData[i] = null;
        }
        size = 0;
    }

    public void addAll(ArrayList1<E> other)
    {
        checkCapacity(size + other.size);
        for (int i = 0; i < other.size; ++i)
        {
            add(other.get(i));
        }
    }
    @SuppressWarnings("unchecked")
    public void ensureCapacity(int capacity)
    {
        if (capacity > elementData.length)
        {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity)
            {
                newCapacity = capacity;
            }
            E[] newList = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; ++i)
            {
                newList[i] = elementData[i];
            }
            elementData = newList;
        }
    }

    public ArrayListIterator iterator()
    {
        return new ArrayListIterator();
    }
}
