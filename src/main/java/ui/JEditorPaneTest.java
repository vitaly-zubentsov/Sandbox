package ui;// Браузер на основе редактора JEditorPane

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class JEditorPaneTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private  JEditorPane  editor ;  // наш редактор
	private  JTextField   url; 	// текстовое поле с адресом
	private  final String unavailable = "Адрес недоступен";
	
	public JEditorPaneTest()
	{
		super("Пример с JEditorPane");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создаие пользовательского интерфейса
		createGUI();
		// Вывод окна на экран
		setSize(500, 400);
		setVisible(true);
	}
	/**
	 * Процедура создания интерфейса
	 */
	private void createGUI()
	{
		// Панель с адресной строкой
		JPanel pnlURL = new JPanel();
		pnlURL.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlURL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		// Поле URL адреса
		url = new JTextField("http://mail.ru", 35);
		// Слушатель окончания ввода
		url.addActionListener(new URLAction());
		pnlURL.add(new JLabel("URL :"));
		pnlURL.add(url);
		try {
			// Создание редактора
			editor = new JEditorPane(url.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, unavailable);
		}
		editor.setContentType("text/html");
		editor.setEditable(false);
		// Поддержка ссылок
		editor.addHyperlinkListener(new LinkListener());
		// Размещение в форме
		getContentPane().add(pnlURL, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(editor));
	}
	// Слушатель, получающий уведомления о вводе нового адреса
	class URLAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Переход по адресу
			String newAddress = url.getText();
			try {
				editor.setPage(newAddress);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(JEditorPaneTest.this, unavailable);
			}
		}
	}
	// Слушатель, обеспечивающий поддержку ссылок
	class LinkListener implements HyperlinkListener {
		public void hyperlinkUpdate(HyperlinkEvent he) {
			// Проверка типа события
			if ( he.getEventType() != HyperlinkEvent.EventType.ACTIVATED ) 
				return;
			// Переходим по адресу
			try {
				editor.setPage(he.getURL());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(JEditorPaneTest.this, unavailable);
			}
		}
	}
	public static void main(String[] args) {
		new JEditorPaneTest();
	}
}
