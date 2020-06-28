package socketIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ConnectionWorker2 implements Runnable {
    // Сокет, через который происходит обмен данными с клиентом
    private Socket clientSocket = null;

    // Входной поток, через который получаем данные с сокета
    private InputStream inputStream = null;

    public ConnectionWorker2(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {

        // Получаем входной поток
        try {
            inputStream = clientSocket.getInputStream();
        } catch (IOException e) {
            System.out.println("Cannot get input stream, because :" + e.getMessage());
        }

        // Создаём буфер для данных
        byte[] buffer = new byte[1024 * 4];

        while (true) {

            try {
                /*
                 * получаем очередную порцию данных
                 * в переменной count храниться реальное количество байт, которое получили
                 */
                int count = inputStream.read(buffer, 0, buffer.length);

                // Проверяем, какое количество байт к нам пришло
                if (count > 0) {
                    System.out.println(new String(buffer, 0, count));
                } else {
                    //если мы получили -1, занчит прервался наш поток с данными и соединение надо закрыть.
                    if (count == -1) {
                        System.out.println("initialization operation to close this socket");
                        clientSocket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
