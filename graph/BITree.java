
/**
 * Fenwick Tree (Binary Indexed Tree)
 */

class BITree {
    int BITree[];

    BITree(int[] ar) {
        BITree = new int[ar.length + 1];
    }

    int getSum(int index) {
        int sum = 0;
        index = index + 1;
        while (index > 0) {
            sum += BITree[index];
            index -= index & (-index);
        }
        return sum;
    }

    public void update(int index, int val) {
        index = index + 1;

        while (index <= BITree.length) {
            BITree[index] += val;
            index += index & (-index);
        }
    }

    public static void main(String[] args) {
        int[] ar = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };

        BITree tree = new BITree(ar);

        for (int i = 0; i < ar.length; i++) {
            tree.update(i, ar[i]);
        }

        System.out.println(tree.getSum(5)); // expected: 12
        tree.update(3, 6); // ar[3] = 6;
        System.out.println(tree.getSum(5)); // expected: 18

    }
}