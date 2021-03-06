package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class SocketServer {

    private static SocketServer socketServer;
    private final ServerSocket server;

    private SocketServer(int portNumber) throws IOException {
        server = new ServerSocket(portNumber);
        server.getReceiveBufferSize();
    }

    static public SocketServer getSocketServer(int portNumber) throws IOException {
        if (socketServer == null) {
            socketServer = new SocketServer(portNumber);
        }
        return socketServer;

    }


    public static void main(String[] args) throws IOException {

        int portNumber = 4999;

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
            Socket socket = socketServer.server.accept();
            Thread thread = new Thread(() -> {
                try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    System.out.println(reader.readLine());
                    Thread.sleep(2000);
                    writer.write("Hello you too\n");
                } catch (InterruptedException e) {
                    System.out.println("Поток для обработки сокет соединения не смог кореектно отработать wait");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Поток для обработки сокет соединения не смог кореектно отработать с потоками");
                    e.printStackTrace();
                }
            });
            thread.start();

        }


    }

}
