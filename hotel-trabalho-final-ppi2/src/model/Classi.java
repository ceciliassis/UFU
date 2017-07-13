package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class Classi {
	private int id_hotel;
	private int id_classifica;
	private String msg;
	private int id_hospede;
	private String data;
	private Connection cc;

	public Classi() {

	}

	public Classi(String msg, String data, int id_classifica) {
		this.data = data;
		this.msg = msg;
		this.id_classifica = id_classifica;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public int getId_classifica() {
		return id_classifica;
	}

	public void setId_classifica(int id_classifica) {
		this.id_classifica = id_classifica;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId_hospede() {
		return id_hospede;
	}

	public void setId_hospede(int id_hospede) {
		this.id_hospede = id_hospede;
	}

	public void fazerClass(String id_hotel, String id_hospede, String msg) {
		int hid = Integer.parseInt(id_hotel);
		int uid = Integer.parseInt(id_hospede);

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		Connection con = null;

		int i = 0;
		int i2 = 0;

		try {
			cc = ConnectionFactory.getConnection();
			con = cc;
			i = contaClass();
			i = i + 8000;

			ps = con.prepareStatement(
					"insert into classifica(data,id_hospede,id_hotel,mensagem,id_classifica) values(now(),?,?,?,?)");
			ps.setInt(1, uid);
			ps.setInt(2, hid);
			ps.setString(3, msg);
			ps.setInt(4, i);
			ps.executeUpdate();

			ps2 = con.prepareStatement("select controle from classifica where id_classifica=?");
			ps2.setInt(1, i);
			rs = ps2.executeQuery();
			if (rs.next())
				i2 = rs.getInt(1);

			ps3 = con.prepareStatement("update classifica set id_classifica=? where id_classifica=?");
			ps3.setInt(1, i2);
			ps3.setInt(2, i);
			ps3.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(null, ps3);
			ConnectionFactory.closeConnection(null, ps2);
			ConnectionFactory.closeConnection(cc, ps, rs);
		}
	}

	public boolean alterarClass(String cid, String msg) {
		int cid2 = Integer.parseInt(cid);
		PreparedStatement ps = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "update classifica set mensagem=? where id_classifica=?";
			ps = cc.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, cid2);

			if (ps.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return false;
	}

	public int buscaHospesde(String cid) {

		int cid2 = Integer.parseInt(cid);
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select id_hospede from classifica where id_classifica=?";
			ps = cc.prepareStatement(sql);

			ps.setInt(1, cid2);

			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return 0;
	}

	public boolean excluirClass(String cid) {
		int cid2 = Integer.parseInt(cid);
		PreparedStatement ps = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "delete from classifica where id_classifica=?";
			ps = cc.prepareStatement(sql);
			ps.setInt(1, cid2);

			if (ps.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return false;
	}

	public int contaClass() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select count(*) from classifica";
			ps = cc.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next())
				return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return 0;
	}

	public List mostrarClassis(int uid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select data, mensagem,id_classifica from classifica where id_hospede=?";

			ps = cc.prepareStatement(sql);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			List<Classi> list = new ArrayList<>();
			while (rs.next()) {

				String data = rs.getString(1);
				String mensagem = rs.getString(2);
				int id = rs.getInt(3);

				list.add(new Classi(mensagem, data, id));

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps, rs);
		}
		return null;
	}

	public void buscarClassi(String cid) {
		int id = Integer.parseInt(cid);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select data, mensagem from classifica where id_classifica=?";

			ps = cc.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {

				String data = rs.getString(1);
				String mensagem = rs.getString(2);

				setData(data);
				setMsg(mensagem);
				setId_classifica(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps, rs);
		}

	}
}
