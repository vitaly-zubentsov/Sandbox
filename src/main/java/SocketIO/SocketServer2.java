package SocketIO;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer2 implements Runnable {

    //Реализуем шаблон Singleton
    private static volatile SocketServer2 instance = null;

    //Порт, на который сервер принимает соединения
    private int serverPort;

    //Сокет, который обрабатывает соединения на сервере
    private ServerSocket serverSocket = null;

    private SocketServer2(int serverPort) {
        this.serverPort = serverPort;
    }

    public static SocketServer2 getServer(int serverPort) {
        if (instance == null) {
            synchronized (SocketServer2.class) {
                if (instance == null) {
                    instance = new SocketServer2(serverPort);
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {

        try {
            //Создаём серверный сокет, который принимает соединения
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Start server on port: " + serverPort);

            //старт приёма соединений на сервер
            while (true) {

                ConnectionWorker2 worker = null;

                try {
                    //Ждём нового соединения
                    worker = new ConnectionWorker2(serverSocket.accept());
                    System.out.println("Get client connection");

                    //Создаётся новый поток, в котором обрабатывается соединение
                    Thread t = new Thread(worker);
                    t.start();

                } catch (Exception e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Cant start server on port " + serverPort + " : " + e.getMessage());
        } finally {
            //Закрываем соединение
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Cannot close server socket, because :" + e.getMessage());
                }
            }
        }
    }

/*    public static void main (String[] args) {
        SocketServer socketServer = SocketServer.getServer(4999);
        Thread t = new Thread(socketServer);
        t.start();
    }*/

}
