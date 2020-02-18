package lambda;

public class Ex4 {

    interface ExampleString {
        String compareString(String s1, String s2);
    }

    public static void main(String[] args) {

        ExampleString exampleString = (String s1, String s2) -> {
            if (s1.length() > s2.length()) {
                return s1;
            } else return s2;
        };
        System.out.println(exampleString.compareString("124 thirst", "123"));
    }
}

