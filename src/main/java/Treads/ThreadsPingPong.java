package Treads;

public class ThreadsPingPong {

    public static void main(String[] args) {
        class Sync {
            double x = 1;
            //значение true если работает 1 поток(ping), значение false если работает 2 поток(pong)
            boolean isFirstThreadPing = true;
        }

        Sync sync = new Sync();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (sync) {
                    while (!sync.isFirstThreadPing) {
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    sync.x = Math.sin(sync.x);
                    System.out.printf("%s : x = %f \n", Thread.currentThread().getName(), sync.x);
                    sync.isFirstThreadPing = false;
                    sync.notify();
                }
            }

        }, "Thread Ping");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (sync) {
                    while (sync.isFirstThreadPing) {
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sync.x = Math.asin(sync.x);
                    System.out.printf("%s : x = %f \n", Thread.currentThread().getName(), sync.x);
                    sync.isFirstThreadPing = true;
                    sync.notify();
                }
            }

        }, "Thread Pong");
        t1.start();
/*        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        t2.start();

    }
}
