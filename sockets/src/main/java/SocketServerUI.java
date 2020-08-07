import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Реализвация сокет сервера с использованием UI
 */
public class SocketServerUI extends JFrame {

    int portNumber;
    static JTextArea textAreaToShowSocketMessages;

    SocketServerUI() throws IOException {
        super("Messages from client");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Создаём текстовое поле с возможностью прокрутки содержимого
        textAreaToShowSocketMessages = new JTextArea();
        JScrollPane scrollToShowParsedMessages = new JScrollPane(textAreaToShowSocketMessages);

        //Размещаем текстовое поле
        getContentPane().add(scrollToShowParsedMessages);

        //Открываем диалог получения номера порта, на котором будет работать сервер
        JDialog dialogToStartSocketServer = createDialogToStartServer("Input port number", true, 200, 150);
        dialogToStartSocketServer.setVisible(true);

        // Определение размера и открытие окна
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Запускаем сокет сервер используя порт полученный из ui в отдельном потоке
        SocketServer socketServer = SocketServer.getServer(portNumber);
        Thread t = new Thread(socketServer);
        t.start();


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textAreaToShowSocketMessages.setText("Client says : ");
            }
        });
    }

    private JDialog createDialogToStartServer(String title, boolean modal, int width, int height) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(width, height);
        JButton buttonToStartServer = new JButton("Start Server");
        JTextField textFieldForPort = new JTextField("4999");
        dialog.setLayout(new GridLayout(2, 1, 1, 1));
        dialog.add(textFieldForPort);
        dialog.add(buttonToStartServer);

        dialog.setLocationRelativeTo(getContentPane());

        //Получаем данные из поля ввода UI и сохраняем их в глобальную переменную portNumber и закрывам диалог
        buttonToStartServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portNumber = Integer.parseInt(textFieldForPort.getText());
                dialog.dispose();
            }
        });
        return dialog;

    }

    public static class SocketServer implements Runnable {

        //Реализуем шаблон Singleton

        private static volatile SocketServer instance = null;


        //Порт, на который сервер принимает соединения

        private int serverPort;
        //Сокет, который обрабатывает соединения на сервере

        private ServerSocket serverSocket = null;

        private SocketServer(int serverPort) {
            this.serverPort = serverPort;

        }

        public static SocketServer getServer(int serverPort) {
            if (instance == null) {
                synchronized (SocketServer.class) {
                    if (instance == null) {
                        instance = new SocketServer(serverPort);
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

                //Выводим информацию в UI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaToShowSocketMessages.setText("Start server on port: " + serverPort);
                    }
                });

                //старт приёма соединений на сервер
                while (true) {

                    ConnectionWorker worker = null;

                    try {
                        //Ждём нового соединения
                        worker = new ConnectionWorker(serverSocket.accept());

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                textAreaToShowSocketMessages.append("\nGet client connection");
                            }
                        });

                        //Создаётся новый поток, в котором обрабатывается соединение
                        Thread t = new Thread(worker);
                        t.start();

                    } catch (Exception e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                textAreaToShowSocketMessages.append("\nConnection error: " + e.getMessage());
                            }
                        });
                    }
                }

            } catch (IOException e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaToShowSocketMessages.setText("\nCant start server on port " + serverPort + " : " + e.getMessage());
                    }
                });
            } finally {
                //Закрываем соединение
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                textAreaToShowSocketMessages.setText("\nCannot close server socket, because :" + e.getMessage());
                            }
                        });
                    }
                }


            }
        }

        public class ConnectionWorker implements Runnable {

            // Сокет, через который происходит обмен данными с клиентом
            private Socket clientSocket = null;
            // Входной поток, через который получаем данные с сокета

            private InputStream inputStream = null;

            public ConnectionWorker(Socket socket) {
                clientSocket = socket;
            }

            @Override
            public void run() {

                // Получаем входной поток
                try {
                    inputStream = clientSocket.getInputStream();
                } catch (IOException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            textAreaToShowSocketMessages.append("\nCannot get input stream, because :" + e.getMessage());
                        }
                    });
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
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    textAreaToShowSocketMessages.append("\n" + new String(buffer, 0, count));
                                }
                            });
                        } else {
                            //если мы получили -1, занчит прервался наш поток с данными и соединение надо закрыть.
                            if (count == -1) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        textAreaToShowSocketMessages.append("\ninitialization operation to close this socket");
                                    }
                                });
                                clientSocket.close();
                                break;
                            }
                        }
                    } catch (IOException e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                textAreaToShowSocketMessages.append("\n" + e.getMessage());
                            }
                        });
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SocketServerUI socketServerUI = new SocketServerUI();
    }
}






