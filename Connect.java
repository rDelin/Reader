package core;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {
	public static java.sql.Connection doconnection() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection a=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","joel","joel");
		return a;
	}
}
