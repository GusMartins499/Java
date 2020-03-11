package dao;

import java.security.GeneralSecurityException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Questao;
import util.Constants;

public class QuestaoDAO {

	public void inserir(Questao q) {
		String sql = "insert into perguntas (enunciado,resp1,resp2,resp3,resp4,materia) values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, q.getEnunciado());
			ps.setString(2, q.getResp1());
			ps.setString(3, q.getResp2());
			ps.setString(4, q.getResp3());
			ps.setString(5, q.getResp4());
			ps.setString(6, q.getMateria().getNome());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Questao> ListaMath() {
		ArrayList<Questao> lista = new ArrayList<Questao>();
		String sql = "Select * from perguntas where materia = 'Inglês' order by id";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			//ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Questao q = new Questao();
				q.setId(rs.getInt("id"));
				q.setEnunciado(rs.getString("enunciado"));
				q.setResp1(rs.getString("resp1"));
				q.setResp2(rs.getString("resp2"));
				q.setResp3(rs.getString("resp3"));
				q.setResp4(rs.getString("resp4"));
				lista.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Integer> listaIdMath() {
		ArrayList<Integer> listaIdMath = new ArrayList<Integer>();
		String sql = "Select id from perguntas where materia = 'Matemática'";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listaIdMath.add(rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaIdMath;
	}
}
