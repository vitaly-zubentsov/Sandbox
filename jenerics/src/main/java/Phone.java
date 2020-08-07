public class Phone extends Product<Phone> {

    String model;

    Phone(String model, String name, int price) {
        super(name, price);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {

        return String.format("Name - %s; Model - %s; Price - %d", this.name, this.model, this.price);
    }

    @Override
    int subCompareTo(Phone p) {
        return this.model.compareTo(((Phone) p).model);
    }

  /*  @Override
    int subCompareTo(Product p) {
        try {
            if (p instanceof Phone) {
                return this.model.compareTo(((Phone) p).model);
            }
        } catch (Exception ex) {
            new Exception("Compare phone with No phone is invalid");
        }
        return 0;
    }*/
}
