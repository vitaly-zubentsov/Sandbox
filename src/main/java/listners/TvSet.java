package listners;

public class TvSet implements ElectricityListener{

    @Override
    public void electricity(Object o) {
        System.out.println("Телевизор включился");
    }
}
