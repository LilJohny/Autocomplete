package ua.edu.ucu.utils;

import ua.edu.ucu.utils.immutable.ImmutableArrayList;
import ua.edu.ucu.utils.immutable.ImmutableLinkedList;
import ua.edu.ucu.utils.immutable.ImmutableList;

import java.util.Arrays;

public class Dispatcher {
    public static void main(String[] args) {
        functionalityTest(new ImmutableLinkedList());
        functionalityTest(new ImmutableArrayList());
        System.out.println("Congratulation! You make good work");
    }

    public static void functionalityTest(ImmutableList testList) {
        ImmutableList a = testList;
        a = a.add(1);
//        System.out.println(a.toString());
        sameResult(a, new Object[]{1});
        a = a.add(7);
        a = a.add(1, 3);
//        System.out.println(a.toString());
        sameResult(a, new Object[]{1, 3, 7});
        a = a.addAll(2, new Object[]{4, 5, 6});
//        System.out.println(a.toString());
        sameResult(a, new Object[]{1, 3, 4, 5, 6, 7});
        a = a.add(1, 2);
//        System.out.println(a.toString());
        sameResult(a, new Object[]{1, 2, 3, 4, 5, 6, 7});
        a = a.add(1, 2);
//        System.out.println(a.toString());
        sameResult(a, new Object[]{1, 2, 2, 3, 4, 5, 6, 7});


        ImmutableList b = a.remove(1);
//        System.out.println(a.toString());
//        System.out.println(b.toString());
        sameResult(a, new Object[]{1, 2, 2, 3, 4, 5, 6, 7});
        sameResult(b, new Object[]{1, 2, 3, 4, 5, 6, 7});

        b = b.add(8);
        b = b.add(b.size(), 9);
//        System.out.println(b);
        sameResult(b, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

//        System.out.println(b);
        sameResult(b.indexOf(1), 0);
        // test on  == vs equals usage in function indexOf
        sameResult(b.indexOf(new Integer(1)), 0); // = )
        b = b.clear();
//        System.out.println(b);
        sameResult(b, new Object[]{});
    }

    public static void sameResult(ImmutableList result, Object[] target) {
        boolean out = Arrays.equals(result.toArray(), target);
        for (int i = 0; i < result.size(); i++) {
            out = out && result.get(i).equals(target[i]);
        }
        if (!out) {
            throw new AssertionError();
        }
    }

    public static void sameResult(int result, int target) {
        if (result != target) {
            throw new AssertionError();
        }
    }
}