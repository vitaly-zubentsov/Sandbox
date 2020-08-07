package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClients implements Runnable {

    InetAddress inetAddress;
    int portNumber;

    SocketClients(InetAddress inetAddress, int portNumber) {
        this.inetAddress = inetAddress;
        this.portNumber = portNumber;
    }

    public void connectToServer() throws InterruptedException, IOException {

        try (
                Socket socketClient = new Socket(inetAddress, portNumber);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()))
        ) {
            System.out.printf("Сокет клиент %s подключён\n", socketClient);

            writer.write(String.format("Hello from %s\n", Thread.currentThread().getName()));
            writer.flush();

            System.out.println(reader.readLine());
            System.out.printf("%s завершает подключение\n", Thread.currentThread().getName());
        }
    }

    @Override
    public void run() {

        try {
            connectToServer();

        } catch (InterruptedException e) {
            System.out.printf("%s не смог корректно отработать wait\n", Thread.currentThread().getName());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.printf("%s не смог создать сокет соединение\n", Thread.currentThread().getName());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {


        int portNumber = 4999;
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Не удалось определить локальный сетевой адрес\n");
            e.printStackTrace();
            throw (e);
        }

        for (int i = 0; i < 100; i++) {
            SocketClients socketClient = new SocketClients(inetAddress, portNumber);
            Thread thread = new Thread(socketClient, String.format("Socket client № %d", i));
            thread.start();

        }

    }

}
