package Treads.pingpong;

public class ThreadsPingPong {

    public static void main(String[] args) {
        class Sync {
            double x = 1;
        }

        Sync sync = new Sync();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
              int i=0;
              while (i<100) {
                  if (sync.x == 1) {
                      synchronized (sync) {
                          sync.x = Math.sin(sync.x);
                          System.out.printf("%s : x = %f \n", Thread.currentThread().getName(),sync.x);
                      }
                  }
                }
            }
        }, "Thread Ping");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<100) {
                    if (!(sync.x == 1)) {
                        synchronized (sync) {
                            sync.x = Math.asin(sync.x);
                            System.out.printf("%s : x = %f \n", Thread.currentThread().getName(),sync.x);
                        }
                    }
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
