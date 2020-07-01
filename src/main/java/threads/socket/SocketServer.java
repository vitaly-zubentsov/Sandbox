package threads.socket;

import java.io.IOException;
import java.net.ServerSocket;

public final class SocketServer {

    private static SocketServer socketServer;
    private ServerSocket server;

    private SocketServer(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
    }

    static public SocketServer getSocketServer(int portNumber) throws IOException {
        if (socketServer == null) {
            socketServer = new SocketServer(portNumber);
        }
        return socketServer;

    }


    public static void main(String[] args) throws IOException, InterruptedException {

        int portNumber = 1076;

        try {
            SocketServer.getSocketServer(portNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Порт указаный при создании сервера находится вне диапазона [0..65535]. " +
                    "Программа завершается");
            e.printStackTrace();
            throw (e);
        } catch (IOException e) {
            System.out.println("Что то пошло не так при создании сервера. Программа завершается");
            e.printStackTrace();
            throw (e);
        }

        while (true) {
            socketServer.server.accept();
            System.out.println("Запуск обработки запроса от клиента");
            Thread.sleep(2000);
            System.out.println("Обработка запроса от клиента завершена");
        }


    }

}
