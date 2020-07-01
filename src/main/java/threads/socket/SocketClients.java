package threads.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClients implements Runnable {

    InetAddress inetAddress;
    int portNumber;

    SocketClients (InetAddress inetAddress, int portNumber) {
        this.inetAddress = inetAddress;
        this.portNumber = portNumber;
    }

    public void connectToServer() throws InterruptedException, IOException {

        try (Socket socketClient = new Socket(inetAddress, portNumber)) {

            System.out.printf("Сокет клиент %s подключён\n", socketClient);

            Thread.sleep(300000);

            System.out.printf("Сокет клиент %s завершает подключение\n", socketClient);

        }
    }

    @Override
    public void run() {

        try {
            connectToServer();
        } catch (InterruptedException e) {
            System.out.printf("Поток %s не смог корректно отработать wait\n", Thread.currentThread().getName());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.printf("Поток %s не смог создать сокет соединение\n", Thread.currentThread().getName());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {


        int portNumber = 1076;
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Не удалось определить локальный сетевой адрес");
            e.printStackTrace();
            throw (e);
        }

        for (int i=0; i<100; i++) {
            SocketClients socketClient = new SocketClients(inetAddress, portNumber);
            Thread thread = new Thread(socketClient);
            thread.start();

        }

    }


}
