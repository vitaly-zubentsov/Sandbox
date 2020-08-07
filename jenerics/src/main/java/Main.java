import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final void main (String[] args) {

        Camera sony = new Camera(1600, "Sony", 1000);
        Camera sory = new Camera(2800, "sory", 150);
        Camera chinaXvory = new Camera(2000, "chinaXvory", 500);

        Phone iPhone = new Phone("iPhone", "iPhone", 800);
        Phone android = new Phone("Android", "Android", 400);

        Container<Product> container = new Container<>();

        container.setItem(sony);
        container.setItem(sory);
        container.setItem(chinaXvory);

        container.setItem(iPhone);
        container.setItem(android);

        container.printItemsInContainer();

        Main main = new Main();
     //   List<?> copyContainer = main.copy(container.getItems(), new ArrayList<>());
        List<Product> copyContainer = new ArrayList<>();
        main.copy(container.getItems(), copyContainer);
        System.out.println(copyContainer);
    }

 //   public List<?> copy (List<? extends Product> src, List<? super Product> dst) {
        public void copy (List<? extends Product> src, List<? super Product> dst) {
        for(Product p: src) {
            dst.add(p);
        }
      //  return dst;
    }
}
