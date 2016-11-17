package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;

public class Reserva {
	private int codigo;
	private String de;
	private String ate;
	private boolean sitPg;
	private String checkIn;
	private String checkOut;
	private int id_hospede;
	private String hotel;
	private int id_hotel;
	private int andar;
	private int quarto;
	private String data;
	private String tipo;

	private Connection cc = null;

	public Reserva() {

	}

	public Reserva(int codigo, String de, String ate, boolean sitPg, String checkIn, String checkOut, int id_hospede,
			String hotel, int andar, int quarto, String data, String tipo) {
		this.codigo = codigo;
		this.de = de;
		this.ate = ate;
		this.sitPg = sitPg;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.id_hospede = id_hospede;
		this.hotel = hotel;
		this.andar = andar;
		this.quarto = quarto;
		this.data = data;
		this.tipo = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public int getAndar() {
		return andar;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}

	public int getQuarto() {
		return quarto;
	}

	public void setQuarto(int quarto) {
		this.quarto = quarto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId_hospede() {
		return id_hospede;
	}

	public void setId_hospede(int id_hospede) {
		this.id_hospede = id_hospede;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getAte() {
		return ate;
	}

	public void setAte(String ate) {
		this.ate = ate;
	}

	public boolean isSitPg() {
		return sitPg;
	}

	public void setSitPg(boolean sitPg) {
		this.sitPg = sitPg;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public void cadastrarReserva(String de, String ate, String id_hotel, String qnum, String qandar,
			String id_hospede) {
		int hid = Integer.parseInt(id_hotel);
		int num = Integer.parseInt(qnum);
		int and = Integer.parseInt(qandar);
		int uid = Integer.parseInt(id_hospede);

		Quarto q = new Quarto();

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		ResultSet rs = null;
		Connection con = null;

		int i = 0;
		int i2 = 0;

		try {
			cc = ConnectionFactory.getConnection();
			con = cc;
			i = contaReserva2();
			i = i + 8000;
			ps = con.prepareStatement("insert into reserva(id_reserva,reserva_de,sitpg,reserva_ate) values(?,?,?,?)");
			ps.setInt(1, i);
			ps.setString(2, de);
			ps.setBoolean(3, false);
			ps.setString(4, ate);
			ps.executeUpdate();

			ps4 = con.prepareStatement("select controle from reserva where id_reserva=?");
			ps4.setInt(1, i);
			rs = ps4.executeQuery();
			if (rs.next())
				i2 = rs.getInt(1);

			ps5 = con.prepareStatement("update reserva set id_reserva=? where id_reserva=?");
			ps5.setInt(1, i2);
			ps5.setInt(2, i);
			ps5.executeUpdate();

			ps2 = con.prepareStatement("insert into faz(data,id_hospede,id_reserva) values (now(),?,?)");
			ps2.setInt(1, uid);
			ps2.setInt(2, i2);
			ps2.executeUpdate();

			ps3 = con.prepareStatement("insert into tem(id_quarto,id_reserva) values (?,?)");
			int qid = q.procuraQuarto(num, and, hid);
			ps3.setInt(1, qid);
			ps3.setInt(2, i2);
			ps3.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps3);
			ConnectionFactory.closeConnection(null, ps2, rs);
			ConnectionFactory.closeConnection(null, ps5);
			ConnectionFactory.closeConnection(null, ps4);
			ConnectionFactory.closeConnection(null, ps, rs);
		}

	}

	public boolean verificaDataDe(String de, String hotel, String quarto, String andar) {
		int hid = Integer.parseInt(hotel);
		int num = Integer.parseInt(quarto);
		int and = Integer.parseInt(andar);

		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			cs = cc.prepareCall("{call verificadata(?,?,?,?)}");
			cs.setString(1, de);
			cs.setInt(2, hid);
			cs.setInt(3, num);
			cs.setInt(4, and);
			rs = cs.executeQuery();

			if (rs.next()) {
				if (rs.getBoolean(1))
					return true;
				else
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;

	}

	public boolean verificaDataAte(String ate, String hotel, String quarto, String andar) {
		int hid = Integer.parseInt(hotel);
		int num = Integer.parseInt(quarto);
		int and = Integer.parseInt(andar);

		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			cs = cc.prepareCall("{call verificaData2(?,?,?,?)}");
			cs.setString(1, ate);
			cs.setInt(2, hid);
			cs.setInt(3, num);
			cs.setInt(4, and);
			rs = cs.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean(1))
					return true;
				else
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, cs, rs);
		}
		return false;

	}

	public int contarReserva(int id) { //para o hospede
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select count(*) from faz, hospede where hospede.id_hospede=? and faz.id_hospede=hospede.id_hospede";
			ps = cc.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next())
				return rs.getInt(1);
			else
				return -1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, ps);
		}
		return 0;

	}

	public int contaReserva2() { //no geral
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cc = ConnectionFactory.getConnection();
			String sql = "select count(*) from reserva";
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

	public boolean excluirReserva(String id) {
		int id_reserva = Integer.parseInt(id);
		PreparedStatement ps = null;
		try {
			cc = ConnectionFactory.getConnection();
			String sql = "delete from reserva where id_reserva = ?";
			ps = cc.prepareStatement(sql);
			ps.setInt(1, id_reserva);

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

	public boolean alterarReserva(String id, String de, String ate) {

		int id_reserva = Integer.parseInt(id);
		PreparedStatement p = null;

		try {
			cc = ConnectionFactory.getConnection();
			p = cc.prepareStatement("update reserva set reserva_de=?,reserva_ate=? where id_reserva=?");
			p.setString(1, de);
			p.setString(2, ate);
			p.setInt(3, id_reserva);

			List<Reserva> list = new ArrayList<>();
			if (p.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p);
		}
		return false;
	}

	public boolean checkIn(String id_reserva, String data) { // recepçao
		int rid = Integer.parseInt(id_reserva);
		PreparedStatement p = null;

		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			p = cc.prepareStatement("update reserva set checkin=? where id_reserva=?");
			p.setString(1, data);
			p.setInt(2, rid);
			if (p.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p, rs);
		}
		return false;
	}

	public boolean checkOut(String id_reserva, String data) { // recepçao
		int rid = Integer.parseInt(id_reserva);
		PreparedStatement p = null;

		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			p = cc.prepareStatement("update reserva set checkin=? where id_reserva=?");
			p.setString(1, data);
			p.setInt(2, rid);
			if (p.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p, rs);
		}
		return false;
	}

	public List mostrarReserva() {
		PreparedStatement p = null;

		ResultSet rs = null;
		try {
			cc = ConnectionFactory.getConnection();
			p = cc.prepareStatement("select * from viewreserva");
			rs = p.executeQuery();
			List<Reserva> list = new ArrayList<>();
			while (rs.next()) {
				int id_hospede = rs.getInt(1); // hospede
				int codigo = rs.getInt(2); // id_reserva
				String de = rs.getString(3); // reserva_de
				String ate = rs.getString(4); // reserva_ate
				String checkin = rs.getString(5); // check in
				String checkout = rs.getString(6); // checkout
				boolean sitPg = rs.getBoolean(7);
				String hotel = rs.getString(8);
				int andar = rs.getInt(9);
				int quarto = rs.getInt(10);
				String data = rs.getString(11);
				String tipo = rs.getString(12);
				int id_hotel = rs.getInt(13);

				list.add(new Reserva(codigo, de, ate, sitPg, checkin, checkout, id_hospede, hotel, andar, quarto, data,
						tipo));

			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p, rs);
		}
		return null;
	}

	public void buscarReserva(String id) {
		int rid = Integer.parseInt(id);
		PreparedStatement p = null;
		ResultSet rs = null;

		try {
			cc = ConnectionFactory.getConnection();
			p = cc.prepareStatement("select codigo,de,ate,hotel,quarto,andar,tipo,id_hotel from viewreserva where codigo=? ");
			p.setInt(1, rid);
			rs = p.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt(1); // id_reserva
				String de = rs.getString(2); // reserva_de
				String ate = rs.getString(3); // reserva_ate
				String hotel = rs.getString(4);
				int andar = rs.getInt(5);
				int quarto = rs.getInt(6);
				String tipo = rs.getString(7);
				int id_hotel= rs.getInt(8);
				
				setCodigo(codigo);
				setDe(de);
				setAte(ate);
				setHotel(hotel);
				setQuarto(quarto);
				setTipo(tipo);
				setAndar(andar);
				setId_hotel(id_hotel);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cc, p, rs);
		}
	}
}
