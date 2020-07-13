package threads.standardlibraries;

import java.util.function.DoubleFunction;

public class Integral {

    public static final int STEPS = 10000000;

    public static double singleThread(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange) {
        return singleThread(doubleFunction, startPointOfRange, endPointOfRange, STEPS);
    }

    static double singleThread(DoubleFunction<Double> doubleFunction, double startPointOfRange, double endPointOfRange, int steps) {
        double widthOfStep = (endPointOfRange - startPointOfRange) / steps;
        double sumOfArea = 0d;

        for (int i = 0; i < steps; i++) {
            double middlePointOfStep = startPointOfRange + widthOfStep * i + widthOfStep / 2;
            double valueOfFunctionAtPoint = doubleFunction.apply(middlePointOfStep);
            sumOfArea += valueOfFunctionAtPoint*widthOfStep;
        }

        return sumOfArea;
    }

    //TODO сделать public static multiThread()
}
