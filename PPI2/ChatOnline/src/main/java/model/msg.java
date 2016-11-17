package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnectionFactory;

public class msg {
	private Connection c;
	private String data;
	private String msgn;
	private int uid;
	
	public msg(){
		
	}

	public msg(String msgn, int uid) {
		this.msgn = msgn;
		this.uid = uid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getMsgn() {
		return msgn;
	}

	public void setMsgn(String msgn) {
		this.msgn = msgn;
	}

	public int contarMsg() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = ConnectionFactory.getConnection();
			String sql = "select count(*) from msg";
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(c, pst, rs);
		}
		return 0;
	}

	public String buscaMsg(int num) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			c = ConnectionFactory.getConnection();

			String sql = "select msg from msg where id=?";

			pst = c.prepareStatement(sql);
			pst.setInt(1, num);

			rs = pst.executeQuery();
			if (rs.next()) {
				setMsgn(rs.getString(1));
				return msgn;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(c, pst, rs);
		}

		return null;

	}

	public String buscaData(int num) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = ConnectionFactory.getConnection();
			String sql = "select data from msg where id = ?";
			pst = c.prepareStatement(sql);

			pst.setInt(1, num);
			rs = pst.executeQuery();

			if (rs.next()) {
				setData(rs.getString(1));
				return data;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(c, pst);
		}
		return null;
	}

	public void addMensagem() {
		PreparedStatement pst = null;
		try {
			c = ConnectionFactory.getConnection();
			String sql = "insert into msg(uid,data,msg) values (?,now(),?)";
			pst = c.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setString(2, msgn);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(c, pst);
		}
	}
}
