package threads.syncronizeArray;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncArray {
    Object[] syncArray = new Object[100];

    Lock lock = new ReentrantLock();
    Condition writeCondition = lock.newCondition();
    Condition readCondition = lock.newCondition();
    int index, indexBefore, indexAfter;


    public void putObjectInArray(Object object) {
        //TODO
    }

    public void getObjectFromArray(Object object) {
        //TODO
    }

}
