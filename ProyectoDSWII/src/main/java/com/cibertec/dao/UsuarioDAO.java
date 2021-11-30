package com.cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.entity.Proveedor;
import com.cibertec.entity.Usuario;
import com.cibertec.util.MysqlConexion;

public class UsuarioDAO {

	public Usuario login(String log,String pas) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario obj = null;
		try {
			conn = MysqlConexion.obtenerConexion();
			String sql = "select * from usuario where login = ? and password =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, log);
			pstm.setString(2, pas);
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Usuario();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));
				obj.setPassword(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return obj;
	}
	
	public Usuario valida(Usuario bean) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Usuario obj = null;
		try {
			conn = MysqlConexion.obtenerConexion();
			String sql = "select * from usuario where login = ? and password =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bean.getLogin());
			pstm.setString(2, bean.getPassword());
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new Usuario();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));
				obj.setPassword(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return obj;
	}
	
	public List<Usuario> listarTodos() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			String sql = "select * from usuario";
			conn = MysqlConexion.obtenerConexion();
			pstm = conn.prepareStatement(sql);
		
			rs = pstm.executeQuery();
			Usuario obj = null;
			while (rs.next()) {
				obj = new Usuario();
				obj.setIdUsuario(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));
				obj.setPassword(rs.getString(6));
				lista.add(obj);
			}
		} catch (Exception e) {
			
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return lista;
	}
	
	public int actualizaUsuario(Usuario p) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "update usuario set  nombre=?,dni=?,apellido=?,login=?,password=? where idUsuario=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getDni());
			pstm.setString(3, p.getApellido());
			pstm.setString(4, p.getLogin());
			pstm.setString(5, p.getPassword());
			pstm.setInt(6, p.getIdUsuario());
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

	public int eliminaProveedor(int idusuario) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "delete from usuario where idUsuario=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idusuario);

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
	public Usuario buscarUsuario(int id) {
		Usuario obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.obtenerConexion();
			String sql = "select *from usuario where idUsuario=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				obj = new Usuario();
				obj.setIdUsuario((rs.getInt(1)));;
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setLogin(rs.getString(5));;
				obj.setPassword(rs.getString(6));
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
}
