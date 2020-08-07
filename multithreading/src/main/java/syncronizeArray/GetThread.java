package syncronizeArray;

public class GetThread extends Thread {

    SyncArray syncArray;

    GetThread(SyncArray syncArray) {
        this.syncArray = syncArray;
    }

    @Override
    public void run() {
        while (true) {
            try {
                syncArray.getObjectFromArray();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
