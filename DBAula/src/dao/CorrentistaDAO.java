package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Correntista;
import util.Constants;

public class CorrentistaDAO { // date acess objet

	public void inserir(Correntista correntista) {
		String sql = "insert into correntista (nome, nascimento) values (?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, correntista.getNome());
			ps.setString(2, correntista.getNascimento());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(Correntista c) {
		String sql = "update correntista set nome=?,nascimento=? where id=?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getNascimento());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inativar(Correntista c) {
		String sql = "update correntista set status = 'I' where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Correntista> listarTodas() {
		ArrayList<Correntista> listaCorrentista = new ArrayList<>();
		String sql = "Select * from correntista where status = 'A' order by nome";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Correntista c = new Correntista();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getString("nascimento"));
				listaCorrentista.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCorrentista;
	}

	public ArrayList<Correntista> filtrar(String filtro) {
		ArrayList<Correntista> listaC = new ArrayList<>();
		String sql = "Select * from correntista where status = 'A' and nome like ? order by nome";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, filtro + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Correntista c = new Correntista();
				c.setId(rs.getInt("id"));
				c.setNascimento(rs.getString("nascimento"));
				c.setNome(rs.getString("nome"));
				listaC.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaC;
	}

	public Correntista buscarPorId(int id) {
		Correntista c = new Correntista();
		String sql = "Select * from correntista where id=?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getString("nascimento"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
}
