package listners;

public class Lamp implements ElectricityListener{

    @Override
    public void electricity(Object o) {
        System.out.println("Лампа зажглась");
    }
}
