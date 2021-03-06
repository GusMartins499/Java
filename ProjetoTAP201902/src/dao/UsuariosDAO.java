package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Usuario;
import util.Constants;

public class UsuariosDAO {

	public void inserir(Usuario u) {
		String sql = "insert into usuarios (login, turma) values (?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, u.getLogin().toUpperCase());
			ps.setString(2, u.getTurma().toUpperCase());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> listarTodas() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "Select * from usuarios";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setTurma(rs.getString("turma"));
				u.setId(rs.getInt("id"));
				u.setPontuacao(rs.getInt("pontuacao"));
				lista.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<String> listarTodasTurmas() {
		ArrayList<String> turmas = new ArrayList<String>();
		String sql = "select distinct turma from usuarios order by login";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				turmas.add(rs.getString("turma"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmas;
	}

	public ArrayList<Usuario> filtrar(String recebeTurma) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "Select distinct login from usuarios where turma = ? order by login";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, recebeTurma);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				lista.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void inserirPontuacao(String u) {
		String sql = "update usuarios set pontuacao = pontuacao + 10 where login = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, u);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
