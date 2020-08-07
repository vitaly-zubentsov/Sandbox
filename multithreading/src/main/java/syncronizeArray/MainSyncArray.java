package syncronizeArray;

/**
 * Класс для проверки работоспосбоности синхронизированного массива
 */
public class MainSyncArray {

    public static void main (String[] args) throws InterruptedException {
        SyncArray syncArray = new SyncArray();

        Thread putThread = new PutThread(syncArray);
        Thread putThread2 = new PutThread(syncArray);
        Thread putThread3 = new PutThread(syncArray);
        Thread getThread = new GetThread(syncArray);

        putThread.start();
        putThread2.start();
        putThread3.start();
        getThread.start();

        Thread.sleep(100000);
        System.exit(0);
    }
}
