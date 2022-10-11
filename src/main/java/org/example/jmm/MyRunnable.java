package org.example.jmm;

public class MyRunnable implements Runnable {
    private volatile int count = 0;

    @Override
    public void run() {
//        methodOne();
        for (int i = 0; i < 1_000_000; i++) {
                this.count++;
        }
        System.out.println(Thread.currentThread().getName() + " : " + this.count);
    }

    public void methodOne() {
        int localVariable1 = 45;
        MySharedObject localVariable2 = MySharedObject.sharedInstance;
        //... do more with local variables.
        methodTwo();
    }

    public void methodTwo() {
        Integer localVariable1 = new Integer(99);
        //... do more with local variable.
    }
}