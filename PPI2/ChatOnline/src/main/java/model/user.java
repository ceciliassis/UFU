package model;
// o model conversa com o BD

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.ConnectionFactory;

public class user {
	private Connection conn;
	private String login;
	private boolean status;
	private String senha;
	private String email;
	private int id;

	public user() {
	}

	public user(String login, String senha) {

		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean verificaLogin() {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();

			String sql = "select id from users where login=? and pwd=?";

			pst = conn.prepareStatement(sql);

			pst.setString(1, login);
			pst.setString(2, senha);

			rs = pst.executeQuery();

			if (rs.next()) {

				setId(rs.getInt(1));
				sql = "update users set status=true where id=?";
				pst2 = conn.prepareStatement(sql);
				pst2.setInt(1, id);
				pst2.executeUpdate();
				return true;
			}

			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pst, rs);
			ConnectionFactory.closeConnection(null, pst2);
		}
		return false;
	}

	public boolean verificaLogin(String cara) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select id from users where login=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, cara);
			rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pst, rs);
		}
		return false;
	}

	public String buscaMsg(int num) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select login from users,msg where msg.uid=users.id and msg.id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, num);
			rs = pst.executeQuery();

			if (rs.next()) {
				setLogin(rs.getString(1));
				return login;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pst, rs);
		}
		return null;
	}

	public void cadastrarUsuario() {
		PreparedStatement pst = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "insert into users(login,pwd,email,status) values (?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, login);
			pst.setString(2, senha);
			pst.setString(3, email);
			pst.setBoolean(4, status);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pst);
		}

	}

	public String buscarLogados() {
		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "{call logados()}";
			cs = conn.prepareCall(sql);
			rs = cs.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, cs, rs);
		}
		return null;
	}
	
	public void logout (){
		PreparedStatement pst = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "update users set status=false where id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pst);
		}
		
	}

}
