import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart<T> {

    String nameOfCart;
    List<T> items = new ArrayList<>();

    Cart(String nameOfCart){
        this.nameOfCart = nameOfCart;
    }

    public List<T> getItems() {
        return items;
    }

    public static void copyCart(List<Product<?>> src, List<Product<?>> dst) {
        dst.addAll(src);
    }

    public static void reverse(List<?> list) {
        rev(list);
    }

    /**
     * Реализация паттерна "Wildcard Capture"
     */
    private static <T> void rev(List<T> list) {
        List<T> tmp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size()-i-1));
        }
    }

    public void setItem(T item) {
        this.items.add(item);
    }

    public void printItemsInCart() {
        System.out.printf("В корзине %s, находятся следующие товары:\n", this.nameOfCart);
        for (T iterator : items) {
            System.out.println(iterator);
        }
    }


}
