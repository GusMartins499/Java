package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Carro;
import util.Constants;

public class CarroDAO {

	public void inserir(Carro c) {
		String sql = "insert into carro (marca,modelo,ano,valor,ar,direcao,piloto,abs,outro) values (?, ?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAno());
			ps.setDouble(4, c.getValor());
			ps.setString(5, c.getAr());
			ps.setString(6, c.getDirecao());
			ps.setString(7, c.getPiloto());
			ps.setString(8, c.getAbs());
			ps.setString(9, c.getOutro());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Carro> lista() {
		ArrayList<Carro> lista = new ArrayList<Carro>();
		String sql = "Select * from carro";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carro c = new Carro();
				c.setMarca(rs.getString("marca"));
				c.setModelo(rs.getString("modelo"));
				c.setAno(rs.getInt("ano"));
				c.setValor(rs.getDouble("valor"));
				c.setAr(rs.getString("ar"));
				c.setDirecao(rs.getString("direcao"));
				c.setPiloto(rs.getString("piloto"));
				c.setAbs(rs.getString("abs"));
				c.setOutro(rs.getString("outro"));
				c.setId(rs.getInt("id"));
				lista.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
