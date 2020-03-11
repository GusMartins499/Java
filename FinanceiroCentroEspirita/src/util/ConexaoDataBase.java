package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDataBase {

	public static Connection conectaBd() {
		Connection conn = null;
		try {
			File f = new File("bd\\DADOS.sqlite");
			if (f.exists()) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:bd\\DADOS.sqlite");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
