package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Pergunta;
import util.Constants;

public class PerguntaDAO {

	public void inserir(Pergunta p) {
		String sql = "insert into perguntas (enunciado,resposta) values (?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, p.getColunaEnunciado());
			ps.setString(2, p.getColunaResposta());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletar(Pergunta p) {
		String sql = "delete from perguntas where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, p.getColunaId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pergunta> listaTodas() {
		ArrayList<Pergunta> lista = new ArrayList<Pergunta>();
		String sql = "Select * from perguntas order by enunciado";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pergunta p = new Pergunta();
				p.setColunaId(rs.getInt("id"));
				p.setColunaEnunciado(rs.getString("enunciado"));
				p.setColunaResposta(rs.getString("resposta"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void alterar(Pergunta p) {
		String sql = "update perguntas set enunciado=?,resposta=? where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, p.getColunaEnunciado());
			ps.setString(2, p.getColunaResposta());
			ps.setInt(3, p.getColunaId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pergunta> filtrar(String filtro) {
		ArrayList<Pergunta> lista = new ArrayList<Pergunta>();
		String sql = "Select * from perguntas where enunciado like ? order by enunciado";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, filtro + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pergunta p = new Pergunta();
				p.setColunaId(rs.getInt("id"));
				p.setColunaEnunciado(rs.getString("enunciado"));
				p.setColunaResposta(rs.getString("resposta"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}

}
