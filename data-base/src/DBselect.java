import java.sql.*;
import java.util.Scanner;

public class DBselect {


	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String CONNETION_STRING = 
			"jdbc:mysql://localhost/web?user=root&password=demo";


	public static void main(String[] args) throws ClassNotFoundException  {
		//Для выполнения программы необходимо добавить драйвер базы данных (sandbox/lib)

		//Загружаем драйвер
		Class.forName(DRIVER_NAME);


		Scanner sc = new Scanner(System.in);
		System.out.println("Input part of a course name to search: ");
		String partOfSearchingCourse = sc.nextLine();

		try (Connection conn = DriverManager.getConnection(CONNETION_STRING);) {
			Statement cmd =  conn.createStatement();
			//Выводим поля таблицы курсов при 
			//совпадении параметра столбца name с подстрокой, введённой пользователем
			String sql = "SELECT name, description, lenght FROM courses WHERE name LIKE '%" 
					+ partOfSearchingCourse +"%' ORDER BY lenght";
			ResultSet result = cmd.executeQuery(sql);
			while (result.next())
			{
				String name = result.getString("name");
				String description = result.getString("description");
				int  lenght = result.getInt("lenght");
				System.out.printf("%-10s : %-10s : %d \n",name, description,lenght);
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
