package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class Quarto {
	private int num;
	private int andar;
	private String tipo;
	private String descricao;
	private int id_hotel;
	private Connection cc;

	public Quarto() {

	}

	public Quarto(int num, int andar, String tipo, String descricao, int id_hotel) {
		this.num = num;
		this.andar = andar;
		this.tipo = tipo;
		this.descricao = descricao;
		this.id_hotel = id_hotel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAndar() {
		return andar;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public List mostrarQuarto(int hid) {
		PreparedStatement p = null;

		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select * from viewquartos where hotel=?";
			p = cc.prepareStatement(sql);
			p.setInt(1, hid);
			rs = p.executeQuery();
			List<Quarto> list = new ArrayList<>();
			while (rs.next()) {
				int num = rs.getInt(1);
				int andar = rs.getInt(2);
				String tipo = rs.getString(3);
				String descricao = rs.getString(4);
				int hotel = rs.getInt(5);
				list.add(new Quarto(num, andar, tipo, descricao, hotel));

			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p, rs);
		}
		return null;
	}

	public int procuraQuarto(int qnum, int qandar, int id_hotel) {
		PreparedStatement p = null;
		ResultSet rs = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select id_quarto from quarto where id_hotel=? and num_quarto=? and andar = ?";
			p = cc.prepareStatement(sql);
			p.setInt(1, id_hotel);
			p.setInt(2, qnum);
			p.setInt(3, qandar);
			rs = p.executeQuery();

			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p, rs);
		}
		return 0;
	}

}
