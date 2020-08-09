package model;

public class Camera extends Product<Camera> {

    private final int pixels;

    public Camera(String name, int price, int pixels) {
        super(name, price);
        this.pixels = pixels;
    }

    @Override
    int subCompareTo(Camera p) {
        return (this.pixels - p.pixels);
    }

    @Override
    public String toString() {
        return String.format("Name - %s; Price - %s; Pixel - %d", this.name, this.price, this.pixels);
    }

}
