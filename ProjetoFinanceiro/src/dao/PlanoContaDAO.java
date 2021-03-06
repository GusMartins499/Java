package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.PlanoConta;
import util.Constants;

public class PlanoContaDAO {

	public void inserir(PlanoConta planoC) {
		String sql = "insert into planoconta (conta,tipoconta) values (?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, planoC.getConta());
			ps.setString(2, planoC.getTipoConta());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> listarTodas() {
		ArrayList<String> lista = new ArrayList<String>();
		String sql = "select * from planoconta order by id";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//PlanoConta planoC = new PlanoConta();
				//planoC.setId(rs.getInt("id"));
				//planoC.setConta(rs.getString("conta"));
				//planoC.setTipoConta(rs.getString("tipoconta"));
				lista.add(rs.getString("conta"));

				//lista.add(planoC);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
