package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class User {

	private Connection cc;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private String cpf;
	private int telefone;
	private int id;
	private short ddd;

	public User() {
	}

	public User(String nome, String login, String senha, String email, String cpf, short ddd, int telefone) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.ddd = ddd;
		this.telefone = telefone;
		this.cpf = cpf;

	}

	public User(String nome, String login, String senha, String email, String cpf, short ddd, int telefone, int id) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.ddd = ddd;
		this.telefone = telefone;
		this.cpf = cpf;
		this.id = id;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getDdd() {
		return ddd;
	}

	public void setDdd(short ddd) {
		this.ddd = ddd;
	}

	public int verificaUser() {
		if (login.equals("recepcao") && senha.equals("recepcao11111")) {
			setNome("Recepção LIVINGS");
			return 1;
		}

		else if (buscaHospede()) // login
			return 2;

		else if (buscaHospede2()) // login existente
			return 3;

		else
			return 0; // login inexistente

	}

	public boolean buscaHospede() { // login
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select nome,id_hospede from hospede where login=? and senha=?";
			ps = cc.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			rs = ps.executeQuery();
			if (rs.next()) {
				setNome(rs.getString(1));
				setId(rs.getInt(2));
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps, rs);
		}
		return false;

	}

	public boolean buscaHospede2() { // login existente

		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;

		try {
			cc = ConnectionFactory.getConnection();
			cs = cc.prepareCall("{call checarlogin(?)}");
			cs.setString(1, login);
			rs = cs.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean(1))
					return true;
				else
					return false;
			}

		} catch (Exception e) {
			return false;
		} finally {
			ConnectionFactory.closeConnection(cc, cs, rs);
			ConnectionFactory.closeConnection(null, ps);
		}

		return false;

	}

	public void buscaHospede3(int uid) { // retorna dados
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select nome, cpf,ddd,tel,email,login from hospede where id_hospede=?";
			ps = cc.prepareStatement(sql);
			ps.setInt(1, uid);
			rs = ps.executeQuery();

			if (rs.next()) {
				setNome(rs.getString(1));
				setCpf(rs.getString(2));
				setDdd((short) rs.getInt(3));
				setTelefone(rs.getInt(4));
				setEmail(rs.getString(5));
				setLogin(rs.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, cs, rs);
			ConnectionFactory.closeConnection(null, ps);
		}

	}

	public boolean cadastrarHospede() {
		PreparedStatement ps = null;
		try {
			cc = ConnectionFactory.getConnection();

			String sql = "insert into hospede(cpf, login, senha,nome,ddd,tel,email) values(?,?,?,?,?,?,?)";
			ps = cc.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, login);
			ps.setString(3, senha);
			ps.setString(4, nome);
			ps.setShort(5, ddd);
			ps.setInt(6, telefone);
			ps.setString(7, email);
			int status = ps.executeUpdate();
			if (status == 1)
				return true;

		} catch (Exception e) {
			return false;
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return false;
	}

	public boolean alterarHospede(String id) {
		int uid = Integer.parseInt(id);
		PreparedStatement ps = null;
		String sql = null;
		try {
			cc = ConnectionFactory.getConnection();
			if (senha == null) {
				sql = "update hospede set nome=?, tel=? , ddd=?,email=? where id_hospede=?";
				ps = cc.prepareStatement(sql);

				ps.setString(1, nome);
				ps.setInt(2, telefone);
				ps.setInt(3, ddd);
				ps.setString(4, email);
				ps.setInt(5, uid);
			}

			else {
				sql = "update hospede set senha=?,nome=?, tel=? , ddd=?,email=? where id_hospede=?";
				ps = cc.prepareStatement(sql);
				ps.setString(1, senha);
				ps.setString(2, nome);
				ps.setInt(3, telefone);
				ps.setInt(4, ddd);
				ps.setString(5, email);
				ps.setInt(6, uid);
			}

			ps.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return false;

	}

	public boolean excluirHospede(int uid) {
		PreparedStatement ps = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "delete from hospede where id_hospede=?";
			ps = cc.prepareStatement(sql);
			ps.setInt(1, uid);
			int status = ps.executeUpdate();
			if (status == 1)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return false;

	}

	public List mostrarHospede() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select * from hospede";

			ps = cc.prepareStatement(sql);

			rs = ps.executeQuery();
			List<User> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String cpf = rs.getString(2);
				String login = rs.getString(3);
				String senha = rs.getString(4);
				String nome = rs.getString(5);
				int tel = rs.getInt(6);
				String email = rs.getString(7);
				short ddd = (short) rs.getInt(8);

				list.add(new User(nome, login, senha, email, cpf, ddd, tel, id));

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps, rs);
		}
		return null;
	}

}