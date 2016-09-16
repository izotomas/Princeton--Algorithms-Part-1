package w3.comparator;

import java.util.Comparator;

/**
 * Created by tomasizo on 9/14/16.
 *
 * Example how to use Comparator Interface
 **/
public class Student {

    public static final Comparator<Student> BY_NAME    = new ByName();
    public static final Comparator<Student> BY_SECTION = new BySection();

    private final String name;
    private final int section;

    public Student(String name, int section) {
        this.name = name;
        this.section = section;
    }

    private static class ByName implements Comparator<Student>
    {
        public int compare(Student v, Student w)
        {  return v.name.compareTo(w.name);  }
    }

    private static class BySection implements Comparator<Student>
    {
        public int compare(Student v, Student w)
        {  return v.section - w.section;  }
    }

}
