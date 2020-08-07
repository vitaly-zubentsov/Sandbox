import java.util.ArrayList;
import java.util.List;

public class Container<T extends Product> {

    List<T> items = new ArrayList<>();

    public List<T> getItems() {
        return items;
    }

    public void setItem(T item) {
        this.items.add(item);
    }

    public void printItemsInContainer () {
        for(T iterator : items) {
            System.out.println(iterator);
        }
    }
}
