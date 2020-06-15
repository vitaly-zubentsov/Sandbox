package memory;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Memory extends Salary2{
   public  int value = 10;

    public static void main (String[] args) {

        Memory memory = new Memory();

        System.out.println(memory.value);
        System.out.println(memory.value2);
        System.out.println( ((Salary2) memory).value);

        List rawlist = new ArrayList();
        List<String> list = new ArrayList<>();

        rawlist =list;
        rawlist.add(8);

        String s = list.get(0);



       // Salary2.Percusion percusion = new Salary2.Percusion();

//        Mem blalba = new Mem("Statjlakhk");
//
//        Integer integer = 5;
//        Integer.sum (integer, 5);
//
//        calculate(blalba, integer);
//        System.out.println(blalba.name);
//        System.out.println(integer);
    }

    //TODO: проверить что это работает

    static void calculate (final Mem name, Integer number) {
        System.out.println(number);
        name.changeName("finish");
      //  number.fd = 10;
        System.out.println(name.name);
        System.out.println(number);

    }

    //fsdfhdks это просто коментарий прикольно
    public static class Mem {

        String name;

        Mem (String name) {
            this.name = name;
        }

        public void changeName(String name) {
            this.name = name;
        }

    }



}
