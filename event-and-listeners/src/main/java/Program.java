public class Program {

    public static void main (String[] args) {
        Switcher switcher = new Switcher();

        Lamp lamp = new Lamp();
        TvSet tvSet = new TvSet();

        switcher.addElectricityListener(lamp);
        switcher.addElectricityListener(tvSet);
        switcher.addElectricityListener(o-> System.out.println("Пожар"));

        switcher.switchON();

    }

}
