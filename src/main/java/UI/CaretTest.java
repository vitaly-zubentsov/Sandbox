package UI;// Пример управление текстовым курсором

import javax.swing.*;
import javax.swing.text.*;

public class CaretTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public CaretTest()
	{
		super("Управление курсором");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Многострочное текстовое поле
		JTextArea text = new JTextArea();
		// Вставка текст
		text.append("Пример управления курсором");
		// Создание курсора
		Caret caret = text.getCaret();
		// Выделение текста 
		caret.setDot ( 7);
		caret.moveDot(15);
		// Частота мерцания курсора 
		caret.setBlinkRate(50);

		// Вырезаем текст и выводим его в консоль
		String temp = text.getText().substring(caret.getDot());
		System.out.println (temp);
		temp = text.getText().substring(caret.getMark(), caret.getDot());
		System.out.println (temp);
		// Размещение текстового поля в интерфейсе окна
		getContentPane().add(new JScrollPane(text));
		// Выводим окно
		setSize(320, 240);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new CaretTest();
	}
}