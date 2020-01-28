package common.layout;

// Пример последовательного расположения - FlowLayout

import java.awt.*;
import javax.swing.*;

public class FlowLayoutTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public FlowLayoutTest()
	{
		super("FlowLayout");
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setSize(300, 120);
		// Панель содержимого
		Container container = getContentPane();
		/*
		 * Определение последовательного расположения 
		 * с выравниванием компонентов по центру
		 */
		container.setLayout( new FlowLayout(FlowLayout.CENTER));
		// добавляем компоненты
		container.add( new JButton("Школа"   ));
		container.add( new JButton("Институт"));
		container.add( new JButton("Академия"));
		// Открываем окно
		setVisible(true);
	}
	public static void main(String[] args) {
		new FlowLayoutTest();
	}
}
