package cat.nyaa.nyaacoretester;

import java.util.HashSet;
import java.util.Set;

public class Assertions {
    public static void assertEq(Object a, Object b) {
        if (a == null && b == null) return;
        if (a == null || b == null) {
            System.err.println(String.format(
                    "\n[FAIL]Object not equal:" +
                            "\n[FAIL]  a=%s"+
                            "\n[FAIL]  b=%s",
                    a, b));
            throw new RuntimeException();
        }
        if (!(a.equals(b))) {
            System.err.println(String.format(
                    "\n[FAIL]Object not equal:" +
                            "\n[FAIL]  a=%s" +
                            "\n[FAIL]  b=%s",
                    a, b));
            throw new RuntimeException();
        }
    }

    public static void assertNotEq(Object a, Object b) {
        if (a == null && b == null) {
            System.err.println("\n[FAIL]Object equal: a==b==null");
            throw new RuntimeException();
        }
        if (a == null || b == null) return;
        if (a.equals(b)) {
            System.err.println(String.format("\n[FAIL]Object equal: a==b==%s", a));
            throw new RuntimeException();
        }
    }

    public static Set<String> marks = new HashSet<>();
    public static void mark(String mark) {
        marks.add(mark);
    }

    public static void assertMarked(String mark) {
        if (marks.contains(mark)) return;
        System.err.println(String.format("\n[FAIL]Mark not set: %s", mark));
        throw new RuntimeException();
    }
}
