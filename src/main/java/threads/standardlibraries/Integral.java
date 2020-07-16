package threads.standardlibraries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.function.DoubleFunction;

public class Integral {

    public static final int STEPS = 10000000;

    public static double calculatingInSingleThread(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange) {

        //обработка кореектности входных данных
        if (startPointOfRange > endPointOfRange) {
            return 0;
        }

        return calculatingInSingleThread(doubleFunction, startPointOfRange, endPointOfRange, STEPS);
    }

    static double calculatingInSingleThread(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange, int steps) {
        double widthOfStep = (endPointOfRange - startPointOfRange) / steps;
        double sumOfArea = 0d;

        for (int i = 0; i < steps; i++) {
            double middlePointOfStep = startPointOfRange + widthOfStep * i + widthOfStep / 2;
            double valueOfFunctionAtPoint = doubleFunction.apply(middlePointOfStep);
            sumOfArea += valueOfFunctionAtPoint * widthOfStep;
        }

        return sumOfArea;
    }

    public static double calculatingInMultiThreadWithThreads(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange, int countOfThreads) throws Exception {

        //обработка кореектности входных данных
        if (startPointOfRange > endPointOfRange) {
            return 0;
        }
        if (countOfThreads == 0) {
            throw new Exception("Count of threads can't equals to zero");
        }
        if (countOfThreads < 0) {
            throw new Exception("Count of threads can't be negative number");
        }


        int countOfStepsForSubrange = STEPS / countOfThreads;
        double widthOfSubrange = (endPointOfRange - startPointOfRange) / countOfThreads;

        Thread[] threadsForCalculation = new Thread[countOfThreads];
        final DoubleAdder sumOfArea = new DoubleAdder();
        for (int i = 0; i < countOfThreads; i++) {

            double startPointOfRangeForSubrange = startPointOfRange + i * widthOfSubrange;
            double endPointOfRangeForSubrange = startPointOfRangeForSubrange + widthOfSubrange;

            threadsForCalculation[i] = new Thread(() -> sumOfArea.add(calculatingInSingleThread(doubleFunction, startPointOfRangeForSubrange, endPointOfRangeForSubrange, countOfStepsForSubrange)));
            threadsForCalculation[i].start();
        }

        for (int i = 1; i < countOfThreads; i++) {
            threadsForCalculation[i].join();
        }
        return sumOfArea.doubleValue();
    }

    public static double calculatingInMultiThreadWithPool(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange, int countOfThreads) throws Exception {

        //обработка кореектности входных данных
        if (startPointOfRange > endPointOfRange) {
            return 0;
        }
        if (countOfThreads == 0) {
            throw new Exception("Count of threads can't equals to zero");
        }
        if (countOfThreads < 0) {
            throw new Exception("Count of threads can't be negative number");
        }

        int countOfStepsForSubrange = STEPS / countOfThreads;
        double widthOfSubrange = (endPointOfRange - startPointOfRange) / countOfThreads;

        ExecutorService poolForCalculating = Executors.newFixedThreadPool(countOfThreads);
        Collection<Future<Double>> tasks = new ArrayList<>();
        for (int i = 0; i < countOfThreads; i++) {
            double startPointOfRangeForSubrange = startPointOfRange + i * widthOfSubrange;
            double endPointOfRangeForSubrange = startPointOfRangeForSubrange + widthOfSubrange;
            tasks.add(poolForCalculating.submit(() -> calculatingInSingleThread(doubleFunction, startPointOfRangeForSubrange, endPointOfRangeForSubrange, countOfStepsForSubrange)));
        }

        double sumOfArea = 0d;
        for (Future<Double> task : tasks) {
            sumOfArea += task.get();
        }

        poolForCalculating.shutdown();
        return sumOfArea;
    }

    public static double calculatingInMultiThreadWithStream(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange, int countOfThreads) throws Exception {

        //обработка кореектности входных данных
        if (startPointOfRange > endPointOfRange) {
            return 0;
        }
        if (countOfThreads == 0) {
            throw new Exception("Count of threads can't equals to zero");
        }
        if (countOfThreads < 0) {
            throw new Exception("Count of threads can't be negative number");
        }

        class CoordinatesOfSubArea {
            double startPointOfRangeForSubrange;
            double endPointOfRangeForSubrange;

             CoordinatesOfSubArea(double startPointOfRangeForSubrange,double endPointOfRangeForSubrange){
                 this.startPointOfRangeForSubrange = startPointOfRangeForSubrange;
                 this.endPointOfRangeForSubrange = endPointOfRangeForSubrange;
             }
        }

        Collection<CoordinatesOfSubArea> coordinatesOfSubAreas = new ArrayList<>();
        double widthOfSubrange = (endPointOfRange - startPointOfRange) / countOfThreads;
        for(int i=0; i<countOfThreads; i++) {
            double startPointOfRangeForSubrange = startPointOfRange + i * widthOfSubrange;
            double endPointOfRangeForSubrange = startPointOfRangeForSubrange + widthOfSubrange;
            coordinatesOfSubAreas.add(new CoordinatesOfSubArea(startPointOfRangeForSubrange,endPointOfRangeForSubrange));
        }

       int countOfStepsForSubrange = STEPS / countOfThreads;

       return coordinatesOfSubAreas.parallelStream().mapToDouble((o) -> calculatingInSingleThread(doubleFunction, o.startPointOfRangeForSubrange, o.endPointOfRangeForSubrange, countOfStepsForSubrange)).sum();
    }


}

