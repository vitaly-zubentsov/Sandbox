package threads.syncronizeArray;

public class PutThread extends Thread {

    int counter;
    SyncArray syncArray;

    PutThread(SyncArray syncArray) {
        this.syncArray = syncArray;
    }

    @Override
    public void run() {
        while (true) {
            try {
                syncArray.putObjectInArray(counter);
                counter++;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}