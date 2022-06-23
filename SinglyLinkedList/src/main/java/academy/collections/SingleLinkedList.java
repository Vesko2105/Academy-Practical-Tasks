package academy.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

interface Collection<T> extends Iterable<T> {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void add(T element);

    void clear();
}

interface Stack<T> extends Collection<T> {
    void push(T value);

    T pop();

    T peek();

    default void add(T element) {
        push(element);
    }
}

public class SingleLinkedList<T> implements Stack<T> {
    private int size = 0;
    Node<T> head;

    private static class Node<E> {
        E val;
        Node<E> next;

        public E getVal(){
            return val;
        }

        public Node(E val) {
            this.val = val;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public void push(T value) {
        size++;
        var newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public T pop() {
        checkNotEmpty();
        size--;
        var res = head.val;
        head = head.next;
        return res;
    }

    @Override
    public T peek() {
        checkNotEmpty();
        return head.val;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    class StackIterator implements Iterator<T>{
        Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(current == null)
                throw new NoSuchElementException();

            var toReturn = current.val;
            current = current.next;
            return toReturn;
        }
    }
}
