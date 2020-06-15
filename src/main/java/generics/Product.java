package generics;

abstract public class Product <T extends Product<T>> implements Comparable<T> {

    protected String name;
    protected int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(T o) {
        return ((this.price) - (o.price));
    }

 //   abstract int subCompareTo (Product p);
    abstract int subCompareTo (T p);
}
