
/**
 * Fenwick Tree (Binary Indexed Tree)
 */
import java.util.*;

class BITree {
    int BITree[];

    BITree(int[] ar) {
        BITree = new int[ar.length + 1];
    }
    

    public static void main(String[] args) {
        int[] ar = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };

        BITree tree = new BITree(ar);
        System.out.println(tree.getSum(5)); // expected: 12
        tree.update(3, 6); // ar[3] = 6;
        System.out.println(tree.getSum(5)); // expected: 18

    }
} 