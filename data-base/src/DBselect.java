import java.sql.*;

public class DBselect {


	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String CONNETION_STRING = 
			"jdbc:mysql://localhost/web?user=root&password=demo";


	public static void main(String[] args) throws ClassNotFoundException  {
		//Нужно добавить драйвер базы данных (sandbox/lib)
		Class.forName(DRIVER_NAME);

		try (Connection conn = DriverManager.getConnection(CONNETION_STRING);) {
			Statement cmd =  conn.createStatement();
			String sql = "SELECT name, description, lenght FROM courses ORDER BY lenght";
			ResultSet result = cmd.executeQuery(sql);
			while (result.next())
			{
				String name = result.getString("name");
				String description = result.getString("description");
				int  lenght = result.getInt("lenght");
				System.out.printf("%-10s : %-10s : %d \n",name, description,lenght);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

