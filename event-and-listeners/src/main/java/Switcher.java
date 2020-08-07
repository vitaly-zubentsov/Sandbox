import java.util.ArrayList;

public class Switcher {

    ArrayList<ElectricityListener> listeners = new ArrayList<>();

    public void addElectricityListener(ElectricityListener electricityListener) {
        listeners.add(electricityListener);
    }

    public void removeElectricityListener(ElectricityListener electricityListener) {
        listeners.add(electricityListener);
    }

    public void switchON() {
        System.out.println("Выключатель включился");

        for (ElectricityListener l : listeners) {
            l.electricity(this);
        }


    }
}
