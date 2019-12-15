package UI;// Пример использования поля JFormattedTextField
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import java.util.Date;

public class JFormattedTextFieldTest extends JFrame
{
	private static final long serialVersionUID = 1L;

	// Форматированное поле мобильного телефона
	private JFormattedTextField ftfPhone;
	
	public JFormattedTextFieldTest()
	{
		super("Пример JFormattedTextField");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			// Определение маски и содание поля ввода мобильного телефона
			MaskFormatter phoneFormatter = new MaskFormatter("+#-###-###-##-##");
			phoneFormatter.setPlaceholderCharacter('0');
			ftfPhone = new JFormattedTextField(phoneFormatter);
			ftfPhone.setColumns(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Определение маски и поля ввода даты
		DateFormat date = new SimpleDateFormat("dd MMMM yyyy, EEEE");
		// Форматирующий объект даты
		DateFormatter dateFormatter = new DateFormatter(date);
		dateFormatter.setAllowsInvalid(false);
		dateFormatter.setOverwriteMode(true);

		// Создание форматированного текстового поля даты
		JFormattedTextField ftfDate = new JFormattedTextField(dateFormatter);
		ftfDate.setColumns(32);
		ftfDate.setValue(new Date());
		// Определение маски и поля ввода вещественного числа
//		NumberFormat number = new DecimalFormat("##0.##E0");  формат числа с экспонентой
		NumberFormat number = new DecimalFormat("##0.###");
		JFormattedTextField numberField = 
				new JFormattedTextField(new NumberFormatter(number));
		numberField.setColumns(10);
		numberField.setValue(new Float(123.45));

		// Создание интерфейса окна
		JPanel contents = new JPanel();
		contents.add(new JLabel("Мобильный :"));
		contents.add(ftfPhone);
		contents.add(new JLabel("Текущая дата :"));
		contents.add(ftfDate);
		contents.add(new JLabel("Вещественное число :"));
		contents.add(numberField);
		setContentPane(contents);
		// Выводим окна
		setSize(310, 150);
		setVisible(true);
	}
	public static void main(String[] args) {
		new JFormattedTextFieldTest();
	}
}
