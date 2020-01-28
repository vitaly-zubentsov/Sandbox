package SocketIO.ServerUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerUI extends JFrame {

    int portNumber;

    private static final long serialVersionUID = 1L;

    SocketServerUI() throws IOException {
        super("Messages from client");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Создаём текстовое поле с возможностью прокрутки содержимого
        JTextArea textAreaToShowSocketMessages = new JTextArea();
        JScrollPane scrollToShowParsedMessages = new JScrollPane(textAreaToShowSocketMessages);

        //Размещаем текстовое поле
        getContentPane().add(scrollToShowParsedMessages);

        //Открываем диалог для настройки и создания сервера
        JDialog dialogToStartSocketServer = createDialogToStartServer("Input port number", true, 200, 150);
        dialogToStartSocketServer.setVisible(true);

        // Определение размера и открытие окна
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    textAreaToShowSocketMessages.setText("Ошибка при создании сокет сервера с портом: " + portNumber + "\nс ошибкой: "+ e.toString());
                }
            });
        }

        System.out.println("Server Started");
        Socket s = ss.accept();

        System.out.println("Client connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textAreaToShowSocketMessages.setText("Client says : " + str);
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

        buttonToStartServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portNumber = Integer.parseInt(textFieldForPort.getText());
                textFieldForPort.setText("успех");
                dialog.dispose();

            }
        });
        return dialog;

    }

    public static void main (String[] args) throws IOException {
        SocketServerUI socketServerUI = new SocketServerUI();
    }

}







