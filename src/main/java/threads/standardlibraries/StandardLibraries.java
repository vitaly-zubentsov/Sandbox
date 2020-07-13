package threads.standardlibraries;

public class StandardLibraries {

    public static void main(String[] args) {

        //Расчет в одном потоке
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        Integral.singleThread(Math::sin, 0d, Math.PI/2);
        Integral.singleThread(Math::sin, 0d, Math.PI/2);
        long timeOfStart = System.currentTimeMillis();
        double areaOfFunction = Integral.singleThread(Math::sin, 0d, Math.PI/2);
        long timeOfFinish = System.currentTimeMillis();

        System.out.printf("Single thread: %f Time: %d\n", areaOfFunction, timeOfFinish-timeOfStart);
    }

}
