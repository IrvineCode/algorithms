import java.util.*;

class temp {
    static int MAXN = 9;
    static int[] T = new int[MAXN];

    public static void main(String[] args) {
        int N = 4;
        int[] P = {1, 2, 4, 3};

        int j = N - 1;
        while(j > 0 && P[j-1] < P[j]) {
            j--;
        }

        System.out.println("j: " + j);

        for (int i = j; i < N; i++) {
            inc(P[i]-1);
            System.out.println(Arrays.toString(T));
        }
        for (int i = 0; i < j; i++) {
            System.out.print(P[i] + " : ");
            System.out.println((j-1-i) + getSum(P[i]-1));
            if (i < j - 1)
                System.out.println(" ");
            inc(P[i]-1);
            System.out.println(Arrays.toString(T));
        }
    }

    static void inc(int i) {
        for (i++; i < MAXN; i += (i & -i))
            T[i]++;
    }

    static int getSum(int i) {
        int c = 0;
        for (i++; i > 0; i -= (i & -i))
            c += T[i];
        return c;
    }

}