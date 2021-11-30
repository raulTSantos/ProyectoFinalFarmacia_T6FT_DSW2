package com.cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.entity.Cliente;
import com.cibertec.util.MysqlConexion;
public class ClienteDAO {
	public int insertaCliente(Cliente p) {
		int salida = -1;

		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MysqlConexion.obtenerConexion();

			String sql = "insert into cliente values(null,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);

			pstm.setString(1, p.getRazonSocial());
			pstm.setString(2, p.getRuc());
			pstm.setString(3, p.getDni());
			pstm.setString(4, p.getDireccion());
			pstm.setString(5, p.getTelefono());
			pstm.setString(6, p.getNombre());

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

	public Cliente buscarCliente(int id) {
		Cliente obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.obtenerConexion();
			String sql = "select *from cliente where idCliente=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				obj = new Cliente();
				obj.setIdCliente(rs.getInt(1));
				obj.setRuc(rs.getString(2));
				obj.setDni(rs.getString(3));
				obj.setRazonSocial(rs.getString(4));
				obj.setDireccion(rs.getString(5));
				obj.setTelefono(rs.getString(6));
				obj.setNombre(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return obj;
	}

	public List<Cliente> listaCliente() {
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "select * from cliente";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			Cliente c = null;
			while (rs.next()) {
				c = new Cliente();
				c.setIdCliente(rs.getInt(1));
				c.setRuc(rs.getString(2));
				c.setDni(rs.getString(3));
				c.setRazonSocial(rs.getString(4));
				c.setDireccion(rs.getString(5));
				c.setTelefono(rs.getString(6));
				c.setNombre(rs.getString(7));
				data.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}	

	public int actualizaCliente(Cliente p) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "update cliente set  ruc=?,dni=?,razonSocial=?,direccion=?,telefono=? where idCliente=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, p.getRuc());
			pstm.setString(2, p.getDni());
			pstm.setString(3, p.getRazonSocial());
			pstm.setString(4, p.getDireccion());
			pstm.setString(5, p.getTelefono());
			pstm.setString(6, p.getNombre());
			pstm.setInt(7, p.getIdCliente());
			actualizados = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}

	public int eliminaCliente(int idcliente) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "delete from cliente where idcliente=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idcliente);

			eliminados = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return eliminados;
	}

}
