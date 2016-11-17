package util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			return DriverManager.getConnection("jdbc:postgresql://localhost/chatOnline2015", "postgres", "postgres");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;		
	}
	public static void closeConnection(Connection conn, Statement st, ResultSet rs) {
		close(conn, st, rs);
	}
	
	public static void closeConnection(Connection conn, Statement st) {
		close(conn, st, null);
	}
	
	public static void closeConnection(Connection conn) {
		close(conn, null, null);
	}
	
	private static void close(Connection conn, Statement st, ResultSet rs) {
		try{
			if (rs!=null) rs.close();
			if (conn!=null) conn.close();
			if (st!=null) st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


