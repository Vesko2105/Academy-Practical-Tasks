package academy.collections;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        var list1 = new SingleLinkedList<String>();
        out.println(list1.size());
        list1.add("Hi");
        list1.add("Hello");
        for (var str : list1) {
            out.println(str);
        }
        out.println(list1.size());
        out.println(list1.peek());
        out.println(list1.pop());
        out.println(list1.size());
        out.println(list1.pop());
        out.println(list1.size());
    }
}
