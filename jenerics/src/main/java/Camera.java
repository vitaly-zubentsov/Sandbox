public class Camera extends Product<Camera>{

    private int pixel;

    Camera (int pixel, String name, int price) {
        super(name, price);
        this.pixel = pixel;
    }

    public int getPixel() {
        return pixel;
    }

    public void setPixel(int pixel) {
        this.pixel = pixel;
    }

    @Override
    public String toString() {
        return String.format("Name - %s; Model - %s; Price - %d", this.name, this.pixel, this.price );
    }

    @Override
    int subCompareTo(Camera p) {
        return (this.pixel - ((Camera) p).pixel);
    }



/*    @Override
    int subCompareTo(Product p) {
        try {
            if ( p instanceof Camera) {
                return (this.pixel - ((Camera) p).pixel);
            }
        } catch (Exception ex) {
            new Exception("Compare camera with No camera is invalid");
        }
        return 0;
    }*/

}
