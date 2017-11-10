package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class Hotel {
	private int codigo;
	private String nome;
	private String endereco;
	private short ddd;
	private int tel;
	private String pais;
	private String cidade;
	private Connection cc;

	public Hotel() {

	}

	public Hotel(int codigo, String nome, String endereco, short ddd, int tel, String pais, String cidade) {

		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.ddd = ddd;
		this.tel = tel;
		this.pais = pais;
		this.cidade = cidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public short getDdd() {
		return ddd;
	}

	public void setDdd(short ddd) {
		this.ddd = ddd;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List mostrarHoteis() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select * from viewHotel";

			ps = cc.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Hotel> list = new ArrayList<>();
			while (rs.next()) {
				int codigo = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				short ddd = (short) rs.getInt(4);
				int tel = rs.getInt(5);
				String pais = rs.getString(6);
				String cidade = rs.getString(7);

				list.add(new Hotel(codigo, nome, endereco, ddd, tel, pais, cidade));

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps, rs);
		}
		return null;
	}

	public String saberNome(String id){
		PreparedStatement ps = null;
		ResultSet rs =null;
		int id2 = Integer.parseInt(id);
		try{
			cc = ConnectionFactory.getConnection();
			String sql = "select nome from hotel where id_hotel=?";
			ps = cc.prepareStatement(sql);
			ps.setInt(1, id2);
			rs = ps.executeQuery();
			if(rs.next())
				return rs.getString(1);			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cc);
		}
		return null ;
	}

}
