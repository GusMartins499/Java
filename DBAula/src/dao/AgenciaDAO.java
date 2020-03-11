package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Agencia;
import util.Constants;

public class AgenciaDAO { // date access objet

	public void inserir(Agencia a) {
		String sql = "insert into agencia (numero, cidade) values (?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, a.getNumero());
			ps.setString(2, a.getCidade());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(Agencia a) {
		String sql = "update agencia set numero=?,cidade=? where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, a.getNumero());
			ps.setString(2, a.getCidade());
			ps.setInt(3, a.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inativar(Agencia a) {
		String sql = "update agencia set status = 'I' where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Agencia> listarTodas() {
		ArrayList<Agencia> lista = new ArrayList<Agencia>();
		String sql = "Select * from agencia where status = 'A' order by cidade";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Agencia a = new Agencia();
				a.setId(rs.getInt("id"));
				a.setCidade(rs.getString("cidade"));
				a.setNumero(rs.getString("numero"));
				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<Agencia> filtrar(String filtro) {
		ArrayList<Agencia> lista = new ArrayList<Agencia>();
		String sql = "Select * from agencia where status = 'A' and cidade like ? order by cidade";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, filtro + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Agencia a = new Agencia();
				a.setId(rs.getInt("id"));
				a.setCidade(rs.getString("cidade"));
				a.setNumero(rs.getString("numero"));
				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}

	public Agencia buscarPorId(int id) {
		Agencia a = new Agencia();
		String sql = "Select * from agencia where id=?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setCidade(rs.getString("cidade"));
				a.setNumero(rs.getString("numero"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
}
