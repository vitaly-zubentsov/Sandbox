package lambda;

public class Ex5 {

    interface ExampleDouble {
        Double compareString(Double a, Double b, Double c);
    }

    public static void main(String[] args) {

        ExampleDouble exampleDouble = (Double a, Double b, Double c) -> (b*b) - 4*a*c;
        System.out.println(exampleDouble.compareString(1.0, 10.0,2.0));

        ExampleDouble exampleDouble2 = (Double a, Double b, Double c) -> a*b*Math.pow(b,c) ;
        System.out.println(exampleDouble2.compareString(1.0, 3.0,2.0));

    }
}

