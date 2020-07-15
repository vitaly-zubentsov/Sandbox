package threads.standardlibraries;

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

        System.out.printf("Single thread: %f Time: %d\n", areaOfFunction2, timeOfFinish2-timeOfStart2);


    }

}
