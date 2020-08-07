package syncronizeArray;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализация синхронизированного массива
 */
public class SyncArray {
    Object[] syncArray = new Object[10];

    Lock lock = new ReentrantLock();
    //Создаём два состояния для обработки ожидания двух различных ситуаций
    //1 - когда массив полон и в него невозможно добавить элементы
    //2 - когда массив пустой и извлечь из него елементы нельзя
    Condition arrayIsFull = lock.newCondition();
    Condition arrayIsEmpty = lock.newCondition();
    int length, putIndex, getIndex;


    /**
     * Функция добавление объекта в массив
     * при попытке добавить объект в уже заполненный массив срабатывает блокировка,
     * при добавлении объекта освобождаем блокировку состояния пустого массива
     */
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

    /**
     * Функция извлечения объекта из массива
     * при попытке извлечения объекта из пустого массива срабатывает блокировка
     * после извлечения объекта освобождаем блокировку состояния полного массива
     */
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
        return syncArray[getIndex];
    }

}
