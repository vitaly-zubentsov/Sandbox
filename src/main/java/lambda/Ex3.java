package lambda;

public class Ex3 {

    interface Example {
        boolean task(int number);
    }

    ;

    public static void main(String[] arg) {


        Example example = (int number) -> {
            return (number % 13) == 0;
        };

        System.out.println(example.task(40));

    }

    ;
}

