package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static final String url = "jdbc:mysql://host/database";
	public static final String user = "seu-usuario";
	public static final String password = "sua-senha";

	public static Connection conn;

	public static Connection getConexao() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, user, password);
				return conn;
			} else {
				return conn;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
