class temp {
    public static void main(String[] args) {
        A a = new B();
        a.fn2();
        
        B b = new B();
        b.fn2();
    }
}

class A {
    int x = 1;
    void fn1() {
        System.out.println(x);
    }

    void fn2() {
        System.out.println(x);
        fn1();
    }
}

class B extends A {
    int x = 2;

    void fn1() {
        System.out.println(x);
    }
}