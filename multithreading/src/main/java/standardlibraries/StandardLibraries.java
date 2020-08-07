package standardlibraries;

/**
 * Класс для подсчёта и вывода значений интеграла непрерывной функции sin от 0 до PI/2
 * с использованием различных алгоритмов обоработки вычислений
 */
public class StandardLibraries {

    public static void main(String[] args) throws Exception {

        //Расчет в одном потоке
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        long timeOfStart = System.currentTimeMillis();
        double areaOfFunction = Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        long timeOfFinish = System.currentTimeMillis();

        System.out.printf("Single thread: %f Time: %d\n", areaOfFunction, timeOfFinish-timeOfStart);

        //Определяем сколько процессоров в системе
        final int COUNT_OF_PROCESSORS_IN_SYSTEM = Runtime.getRuntime().availableProcessors();

        //Расчет в нескольких потоках(10) созданных вручную
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        long timeOfStart2 = System.currentTimeMillis();
        double areaOfFunction2 = Integral.calculatingInMultiThreadWithThreads(Math::sin, 0d, Math.PI/2, COUNT_OF_PROCESSORS_IN_SYSTEM);
        long timeOfFinish2 = System.currentTimeMillis();

        System.out.printf("Threads:       %f Time: %d\n", areaOfFunction2, timeOfFinish2-timeOfStart2);

        //Расчет с использованием пулов(FixedThreadPool c количеством потоков равным 10)
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        long timeOfStart3 = System.currentTimeMillis();
        double areaOfFunction3 = Integral.calculatingInMultiThreadWithPool(Math::sin, 0d, Math.PI/2, COUNT_OF_PROCESSORS_IN_SYSTEM);
        long timeOfFinish3 = System.currentTimeMillis();

        System.out.printf("Pool:          %f Time: %d\n", areaOfFunction3, timeOfFinish3-timeOfStart3);

        //Расчет с использованием стрима
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        Integral.calculatingInSingleThread(Math::sin, 0d, Math.PI/2);
        long timeOfStart4 = System.currentTimeMillis();
        double areaOfFunction4 = Integral.calculatingInMultiThreadWithStream(Math::sin, 0d, Math.PI/2, COUNT_OF_PROCESSORS_IN_SYSTEM);
        long timeOfFinish4 = System.currentTimeMillis();

        System.out.printf("Stream:        %f Time: %d\n", areaOfFunction4, timeOfFinish4-timeOfStart4);
    }

}
