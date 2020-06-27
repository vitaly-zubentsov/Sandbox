package Treads;



public class ThreadNotify1 {

    public static void main(String[] args) {

        class Sync
        {
            int counter;
        }

        Sync sync = new Sync();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.printf("%s : %d \n", Thread.currentThread().getName(), i);
                if (i>10) {
                    System.out.println("interrupt");
                    synchronized (sync) {
                        sync.counter = i;
                        sync.notify();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {

            try {

                while (sync.counter<50) {
                    synchronized (sync) {
                        sync.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s is interrupted \n", Thread.currentThread().getName());
            for (int i = 0; i < 100; i++) {
                System.out.printf("%s : %d \n", Thread.currentThread().getName(), i);
                            }
        });

        t1.start();
/*        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        t2.start();

    }
}
