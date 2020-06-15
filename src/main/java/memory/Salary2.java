package memory;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

class Salary2
{
    public int value = 1000;
    int value2 =2;
    Salary2()
    {
    }
    Salary2(Salary2 x)
    {
        x.value = x.value * 3;
    }
    public static void main(String args[])
    {
        Salary2 s1 = new Salary2();
        Salary2 s2 = new Salary2(s1);
        System.out.println("s1.value=" +s1.value);
        System.out.println("s2.value="+s2.value);

    }

    static class Percusion {

        Percusion () {
            System.out.println("Make a sound");
        }

    }
}