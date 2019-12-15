package UI;// Текстовое поле с поддержкой автозаполнения

import java.util.*;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

public class AutoCompleteField extends JTextField
{
	private static final long serialVersionUID = 1L;
	// Количество начальных символов
	private int firstChars = 3;
	// Список слов для автозаполнения
	private List<String> words = new ArrayList<String>();
	

	// Конструктор
	public AutoCompleteField()
	{
		super();
		getDocument().addDocumentListener(new ChangeListener());
	}
	// Добавление слова в список для автозаполнения
	public void addWord(String word) {
		words.add(word);
	}
	// Добавление списка слов для автозаполнения
	public void addWords(List<String> list) {
		for (String word : list)
			words.add(word);
	}
	public void setBeforeCompletion(int value) {
		firstChars = value;
	}
	// Класс контроля изменений в документе
	class ChangeListener implements DocumentListener {
		// Метод вызывается при вставке в документ нового символа
		public void insertUpdate(DocumentEvent e) {
			// Текущая позицию курсора
			final int pos = e.getOffset() + e.getLength();
			// Модель документа
			final Document doc = e.getDocument();
			// Определение позиции текущего слова
			try {
				int start = Utilities.getWordStart(AutoCompleteField.this, e.getOffset());
				int end   = Utilities.getWordEnd  (AutoCompleteField.this, e.getOffset());
				// Размер (длина) текущего слова
				int templLength = end - start;
				// Проверка размера введенного слова, т.е. можно ли начинать процесс подстановки
				if ( templLength < firstChars)
					return;
				// Текущие символы (слово)
				String templ = doc.getText(start, templLength);
				// Поиск подходящего слова в списке
				String wholeWord = "";
				Iterator<String> i = words.iterator();
				while ( i.hasNext() ) {
					String next = (String)i.next();
					if ( next.startsWith(templ) ) {
						// Вариант подстановки найден
						wholeWord = next;
						break;
					}
				}
				// Завершение, если слово не найдено
				if ( wholeWord == "") 
					return;
				// Вырезаем часть для автозаполнения
				final String toComplete = wholeWord.substring(templLength);
				// Старт задачи подстановки слова - автозаполнение
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							// Удаление Listener
							doc.removeDocumentListener(ChangeListener.this);
							// Вставка окончания слова
							doc.insertString(pos, toComplete, null);
							// Выделение добавленной части
							setSelectionStart(pos);
							setSelectionEnd(pos + toComplete.length());
							// Подключение Listener
							doc.addDocumentListener(ChangeListener.this);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// Процедуры изменения и удаления текста не используются
		public void removeUpdate(DocumentEvent e) {}
		public void changedUpdate(DocumentEvent e) {}
	}
}