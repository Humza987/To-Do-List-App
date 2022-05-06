import java.sql.*;

/*
* Creating a To-Do List App GUI with SQL database to retrieve, save and delete task entries.
* Version 2.0: Added SQL database functionality
* Name: Humza Inam
*/
public class JDBC {
	
	static String url = "jdbc:mysql://localhost:3306/todo";
	static String username = "root"; // insert local sql username here
	static String password = "";  // insert local sql server password here
	static String query = "SELECT * FROM todo.list;";
	static Statement statement2;
	
    	
	public static void main(String[] args) throws SQLException{
		GUI gui = new GUI();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection(url, username, password); 
			Statement statement = con.createStatement();
			statement2 = statement;		
			 
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				String tasks2 = result.getString(1);
				String date = result.getString(2);
				gui.row[0] = tasks2;
				gui.row[1] = date;				
				gui.model.addRow(gui.row);
				
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
