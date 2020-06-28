package ui;// Класс тестирования текстового поля с автоматическим заполнением

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class AutoCompleteFieldTest  extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private  final  List<String>  list = new ArrayList<String>(Arrays.asList("интерполяция", 
			                                                                 "апроксимация")); 
	public AutoCompleteFieldTest()
	{
		super("Тестирование UI.AutoCompleteField");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создание поля
		AutoCompleteField field = new AutoCompleteField();
		field.setColumns(15);
		// Определяем список слов для автозаполнения
		field.addWords(list);
		// Размещаем поле в интерфейсе окна
		JPanel contents = new JPanel();
		contents.add(field);
		setContentPane(contents);
		// Вывод окна
		setSize(240, 100);
		setVisible(true);
	}
	public static void main(String[] args) {
		new AutoCompleteFieldTest();
	}
}