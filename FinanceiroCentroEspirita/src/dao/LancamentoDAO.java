package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Lancamento;
import util.Constants;

public class LancamentoDAO {

	public void inserir(Lancamento l) {
		String sql = "insert into lancamento (planoconta, historico, referencia, emissao, valor, obs) values (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, l.getPlanoConta());
			ps.setString(2, l.getHistorico());
			ps.setString(3, l.getReferencia());
			ps.setString(4, l.getEmissao());
			ps.setDouble(5, l.getValor());
			ps.setString(6, l.getObs());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Lancamento> listarTodas() {
		ArrayList<Lancamento> lista = new ArrayList<Lancamento>();
		String sql = "Select * from lacamento";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lancamento l = new Lancamento();
				l.setId(rs.getInt("id"));
				l.setEmissao(rs.getString("emissao"));
				l.setHistorico(rs.getString("historico"));
				l.setObs(rs.getString("obs"));
				l.setPlanoConta(rs.getString("planoconta"));
				l.setReferencia(rs.getString("referencia"));
				l.setValor(rs.getDouble("valor"));
				lista.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
