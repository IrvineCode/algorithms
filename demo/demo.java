import java.util.*;

class demo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(11);
        list.add(12);
        list.add(0, 100);
        list.remove(3);

        System.out.println(list);
    }
}