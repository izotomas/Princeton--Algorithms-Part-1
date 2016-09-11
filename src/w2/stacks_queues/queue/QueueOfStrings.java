package w2.stacks_queues.queue;

/**
 * Created by tomasizo on 9/11/16.
 */
public class QueueOfStrings {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }
    public QueueOfStrings() {

    }

    public void enueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())  first = last;
        else            oldlast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first       = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return 0;
    }
}
