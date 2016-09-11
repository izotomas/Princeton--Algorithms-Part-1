package w2.stacks_queues.iterable;

import java.util.Iterator;

/**
 * Created by tomasizo on 9/11/16.
 */

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] items;
    private int N = 0;

    public ResizingArrayStack()
    { items = (Item[]) new Object[1]; }

    public void push(Item item) {
        if (N == items.length) resize(2 * items.length );
        items[N++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
           copy[i] = items[i];
        }
        items = copy;
    }

    public Item pop() {
        Item item = items[--N];
        items[N] = null;
        if (N > 0 && N == items.length/4) resize(items.length/2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator {

        private int i = N;

        @Override
        public boolean hasNext() { return i > N; }

        @Override
        public Object next() { return items[--i]; }

        @Override
        public void remove() { /* not supported */ }
    }
}
