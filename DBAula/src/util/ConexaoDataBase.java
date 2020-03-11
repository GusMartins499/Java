package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDataBase {

	public static Connection conectaBd() {
		Connection conn = null;
		try {
			File f = new File("db\\sistema_bancario.sqlite");
			if (f.exists()) {
				Class.forName("org.sqlite.JDBC"); //carrega o driver do bd
				conn = DriverManager.getConnection("jdbc:sqlite:db\\sistema_bancario.sqlite");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
