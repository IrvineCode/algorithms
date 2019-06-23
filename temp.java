class temp {
    public static void main(String[] args) {
        System.out.println(fly(4));
        System.out.println(fly(5));
    }

    static int fun(int x) {
        if (x < 1)
            return 1;
        else
            return x + fun(x - 1) + fun(x - 2);
    }

    static int go(int x) {
        if (x < 1)
            return 1;
        else
            return x + go(x - 2) + go(x - 3);
    }

    static int fly(int x) {
        if (x < 1)
            return 1;
        else
            return x + fly(x - 4) + fly(x - 1);
    }

}