package model;

/**
 * Абстрактный класс, наследники которого должны определять метод subCompareTo(T p),
 * который в свою очередь реализует сравнение каждого вида продукта по своим собственным параметрам (используется
 * рекурсирнове расширение типа)
 */
abstract public class Product<T extends Product<T>>  implements Comparable<T> {

    protected final String name;
    protected final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(T o) {
        return ((this.price) - (o.price));
    }

    /**
     * Абстрактный метод для сравнения объектов одного класса
     */
    abstract int subCompareTo(T p);
}
