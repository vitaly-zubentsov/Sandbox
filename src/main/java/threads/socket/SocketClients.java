package threads.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClients implements AutoCloseable{

    Socket socket;

    SocketClients(InetAddress inetAddress, int portNumber) throws IOException {
        socket = new Socket(inetAddress, portNumber);
    }

    @Override
    public void close() throws Exception {
        if (!socket.isClosed()) {
           socket.close();
       }
    }

    public static void main (String[] args) throws Exception {

        int portNumber = 1076;
        InetAddress inetAddress = InetAddress.getLocalHost();
        try(SocketClients socketClient = new SocketClients(inetAddress, portNumber)) {

            System.out.printf("Сокет клиент %s подключён",socketClient);

            Thread.sleep(3000);

        } catch (IOException e) {
            System.out.println("Что то пошло не так, клиент не смог подключиться к серверу.");
            e.printStackTrace();
            throw (e);
        }


    }
}
