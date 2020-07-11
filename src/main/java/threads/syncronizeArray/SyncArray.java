package threads.syncronizeArray;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncArray {
    Object[] syncArray = new Object[10];

    Lock lock = new ReentrantLock();
    Condition arrayIsFull = lock.newCondition();
    Condition arrayIsEmpty = lock.newCondition();
    int length, putIndex, getIndex;


    public void putObjectInArray(Object object) throws InterruptedException {
        lock.lock();
        try {
            while (length == 10) {
                arrayIsFull.await();
            }
            syncArray[putIndex] = object;
            if (++putIndex == 10) {
                putIndex = 0;
            }
            length++;
            System.out.printf("Array contain - %d element(s)\n", length);
            arrayIsEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    public Object getObjectFromArray() throws InterruptedException {
        lock.lock();
        try {
            while (syncArray.length == 0) {
                arrayIsEmpty.await();
            }
            if (++getIndex == 10) {
                getIndex = 0;
            }
            length--;
            System.out.printf("Array contain - %d element(s)\n", length);
            arrayIsFull.signal();
        } finally {
            lock.unlock();
        }
        return  syncArray[getIndex];
    }

}
