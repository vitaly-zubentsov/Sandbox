package model;

public class AnyProduct extends Product<AnyProduct> {

    public AnyProduct(String name, int price) {
        super(name, price);
    }

    @Override
    int subCompareTo(AnyProduct p) {
        return this.price - p.price;
    }

    @Override
    public String toString() {

        return String.format("Name - %s; Price - %s", this.name, this.price);
    }

}
