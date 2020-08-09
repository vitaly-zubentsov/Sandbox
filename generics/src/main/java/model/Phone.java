package model;

public class Phone extends Product<Phone> {

    private final int sizeOfDisplay;

    public Phone(String name, int price, int sizeOfDisplay) {
        super(name, price);
        this.sizeOfDisplay = sizeOfDisplay;
    }

    @Override
    int subCompareTo(Phone p) {
        return this.sizeOfDisplay - p.sizeOfDisplay;
    }

    @Override
    public String toString() {

        return String.format("Name - %s; Price - %s; Size - %d", this.name, this.price, this.sizeOfDisplay);
    }

    // Определение subCompareTo без использования дженериков
  /*  @Override
    int subCompareTo(product.Product p) {
        try {
            if (p instanceof product.Phone) {
                return this.model.compareTo(((product.Phone) p).model);
            }
        } catch (Exception ex) {
            new Exception("Compare phone with No phone is invalid");
        }
        return 0;
    }*/
}
