package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Materias;
import util.Constants;

public class MateriasDAO {

	public ArrayList<Materias> listarTodas() {
		ArrayList<Materias> lista = new ArrayList<Materias>();
		String sql = "Select nome from materia";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Materias m = new Materias();
				m.setNome(rs.getString("nome"));
				lista.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
