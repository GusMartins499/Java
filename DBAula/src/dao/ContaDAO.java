package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Conta;
import util.Constants;

public class ContaDAO {

	public void inserir(Conta c) {
		String sql = "insert into conta (agencia, correntista) values (?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, c.getAgencia().getId());
			ps.setInt(2, c.getCorrentista().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Conta> listarTodas() {
		AgenciaDAO agDao = new AgenciaDAO();
		CorrentistaDAO corDao = new CorrentistaDAO();
		ArrayList<Conta> lista = new ArrayList<Conta>();
		String sql = "Select * from conta where status = 'A' order by saldo desc";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Conta c = new Conta();
				c.setId(rs.getInt("id"));
				c.setSaldo(rs.getDouble("saldo"));
				c.setAgencia(agDao.buscarPorId(rs.getInt("agencia")));
				c.setCorrentista(corDao.buscarPorId(rs.getInt("correntista")));
				lista.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void inativar(Conta c) {
		String sql = "update conta set status = 'I' where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(Conta c) {
		String sql = "update conta set agencia=?,correntista=? where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, c.getAgencia().getId());
			ps.setInt(2, c.getCorrentista().getId());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizaSaldo(Conta c, double novoSaldo) {
		String sql = "update conta set saldo =? where id = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setDouble(1, novoSaldo);
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Conta> filtraPorCorrentista(String nomeCorrentista) {
		AgenciaDAO agDao = new AgenciaDAO();
		CorrentistaDAO corDao = new CorrentistaDAO();
		ArrayList<Conta> lista = new ArrayList<Conta>();
		String sql = "select conta.id,conta.agencia,conta.correntista, " + "conta.saldo from conta, correntista "
				+ "where conta.correntista = correntista.id " + "and conta.status = 'A' "
				+ "and correntista.nome like ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, nomeCorrentista + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Conta c = new Conta();
				c.setId(rs.getInt("id"));
				c.setSaldo(rs.getDouble("saldo"));
				c.setAgencia(agDao.buscarPorId(rs.getInt("agencia")));
				c.setCorrentista(corDao.buscarPorId(rs.getInt("correntista")));
				lista.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
