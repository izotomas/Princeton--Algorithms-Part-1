package w2.stacks_queues.stack_strings;

/**
 * Created by tomasizo on 9/11/16.
 */
public class StackArrayFixedCapacity {
    private String[] s;
    private int N = 0;

    public StackArrayFixedCapacity(int capacity)
    { s = new String[capacity]; }

    public void push(String item)
    { s[N++] = item; }

    public String pop() {
        String item = s[--N];
        s[N] = null;    // to avoid loitering
        return item;
    }
}
