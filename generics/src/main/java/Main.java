import model.AnyProduct;
import model.Camera;
import model.Phone;
import model.Product;

public class Main {

    public static void main(String[] args) {

        Camera sony = new Camera("Sony", 1000, 1600);
        Camera sory = new Camera("sory", 150, 2800);
        Camera chinaXvory = new Camera("chinaXvory", 500, 2000);

        Phone iPhone = new Phone("iPhone", 600, 5);
        Phone android = new Phone("Android", 500, 6);
        Phone motorola = new Phone("Motorola", 200, 4);

        AnyProduct book = new AnyProduct("Book", 30);
        AnyProduct mouse = new AnyProduct("Mouse", 50);
        AnyProduct keyboard = new AnyProduct("keyboard", 100);

        //Сравнение товаров по ценам
        System.out.println(sony.compareTo(sory));
        //Строка ниже не сработает, так как сравнение возможно только для однотипных объектов
        //sony.compareTo(iPhone);


        Cart<Product<?>> cart = new Cart<>("FirstCart");

        cart.setItem(sony);
        cart.setItem(sory);
        cart.setItem(chinaXvory);

        cart.setItem(iPhone);
        cart.setItem(android);
        cart.setItem(motorola);

        cart.setItem(book);
        cart.setItem(mouse);
        cart.setItem(keyboard);

        //Строка ниже не сработает, так как положить в контейнер мы можем только наследников Product
        //container.setItem("asdflj");

        //Вывод элементов добавленных в корзину
        cart.printItemsInCart();

        //Копирование корзины
        Cart<Product<?>> cartSecond = new Cart<>("SecondCart");
        Cart.copyCart(cart.getItems(), cartSecond.getItems());
        cartSecond.printItemsInCart();

        //Перестановка элементов корзины в обратном порядке
        Cart.reverse(cartSecond.getItems());
        System.out.println("После перестановки элементов");
        cartSecond.printItemsInCart();
    }

}
