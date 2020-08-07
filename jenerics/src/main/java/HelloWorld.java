import java.util.*;
public class HelloWorld {

    public static class Util {
        public static <T extends Integer> T getValue(Object obj) {
            return (T) obj;
        }
    }

    public static void main(String []args) {
//        List list = Arrays.asList(2, 3);
//        for (Object element : list) {
//            System.out.println(Util.getValue(element) + 1);
//        }

        SomeType<String> st = new SomeType<>();
        List<String> list = Arrays.asList("test");
        st.test(list);

        List<String> list2 = new ArrayList<>();
        TestClass.print(list);
    }

    public static class SomeType<T> {
        public <E> void test(Collection<E> collection) {
            for (E element : collection) {
                System.out.println(element);
            }
        }
        public void test(List<Integer> collection) {
            for (Integer element : collection) {
                System.out.println(element);
            }
        }
    }

    public static class TestClass {
        public static void print(List<? super String> list) {
            list.add("Hello World!");
            System.out.println(list.get(0));
        }
    }


}