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




		try (Connection conn = DriverManager.getConnection(CONNETION_STRING);) {

			CallableStatement storedProcedure = conn.prepareCall("call countCourses(?)");

			storedProcedure.execute();
			System.out.printf("Всего строк в таблице: %d\n",storedProcedure.getInt(1));

			Scanner sc = new Scanner(System.in);
			System.out.println("Input part of a course name to search: ");
			String partOfSearchingCourse = sc.nextLine();

			//Подготавливаем параметризированный sql запрос
			String sql = "SELECT name, description, lenght FROM courses WHERE name LIKE ? ORDER BY lenght";
			PreparedStatement cmd = conn.prepareStatement(sql);
			//Подставляем данный в параметризированный sql запрос
			cmd.setString(1, "%" + partOfSearchingCourse + "%" );

			//Выводим поля таблицы курсов при 
			//совпадении параметра столбца name с подстрокой, введённой пользователем
			ResultSet result = cmd.executeQuery();
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

