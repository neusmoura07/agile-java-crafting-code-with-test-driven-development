package Lesson14;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Uma lista circular genérica que mantém um elemento atual.
 * @param <T> tipo dos elementos no Ring
 */
public class Ring<T> implements Iterable<T> {
    private Node current;
    private int size;

    private class Node {
        T data;
        Node next;
        Node prev;
        Node(T data) {
            this.data = data;
        }
    }

    public Ring() {
        current = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna o elemento atual, sem removê-lo.
     */
    public T getCurrent() {
        if (isEmpty())
            throw new NoSuchElementException("Ring vazio");
        return current.data;
    }

    /**
     * Remove e retorna o elemento atual.
     */
    public T removeCurrent() {
        if (isEmpty())
            throw new NoSuchElementException("Ring vazio");
        T data = current.data;
        if (size == 1) {
            current = null;
            size = 0;
        } else {
            Node next = current.next;
            current.prev.next = next;
            next.prev = current.prev;
            current = next;
            size--;
        }
        return data;
    }

    /**
     * Adiciona elemento após o último elemento (mantendo ordem de inserção) e mantém o current no primeiro inserido.
     */
    public void add(T element) {
        Node node = new Node(element);
        if (isEmpty()) {
            node.next = node.prev = node;
            current = node;
        } else {
            // inserir ao final do anel (antes de current)
            Node last = current.prev;
            node.next = current;
            node.prev = last;
            last.next = node;
            current.prev = node;
        }
        size++;
    }

    /**
     * Avança o ponteiro atual em uma posição.
     */
    public void advance() {
        if (isEmpty())
            throw new NoSuchElementException("Ring vazio");
        current = current.next;
    }

    /**
     * Retrocede o ponteiro atual em uma posição.
     */
    public void back() {
        if (isEmpty())
            throw new NoSuchElementException("Ring vazio");
        current = current.prev;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node start = current;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T data = start.data;
                start = start.next;
                count++;
                return data;
            }
        };
    }
}

