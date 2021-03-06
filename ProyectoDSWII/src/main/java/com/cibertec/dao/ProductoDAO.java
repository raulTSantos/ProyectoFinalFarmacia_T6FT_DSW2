package com.cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.entity.Producto;
import com.cibertec.entity.Proveedor;
import com.cibertec.entity.TipoProducto;
import com.cibertec.util.MysqlConexion;

public class ProductoDAO {

	public List<Producto> listarProductoPorNombre(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<Producto> lista = new ArrayList<Producto>();
		try {
			String sql = "select * from producto where nombre like ?";
			conn = MysqlConexion.obtenerConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro + "%");
			rs = pstm.executeQuery();
			Producto objProducto = null;
			TipoProducto beanTipoProducto= null;
			Proveedor beanProveedor = null;

			while (rs.next()) {
				objProducto = new Producto();
				objProducto.setIdProducto(rs.getInt(1));

				beanTipoProducto = new TipoProducto();
				beanTipoProducto.setIdTipoProducto(rs.getInt(2));
				objProducto.setTipoProducto(beanTipoProducto);
				
				beanProveedor = new Proveedor();
				beanProveedor.setIdproveedor(rs.getInt(3));
				
				objProducto.setProveedor(beanProveedor);
				
				objProducto.setNombre(rs.getString(4));			
				objProducto.setPrecioUnitario(rs.getDouble(5));
				objProducto.setStock(rs.getInt(6));
				objProducto.setDescripcion(rs.getString(7));
				
				

				lista.add(objProducto);
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
			} catch (SQLException e) {
			}
		}
		return lista;
	}
	
	
	public int insertaProducto(Producto p) {
		int salida = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "insert into producto values(null,?,?,?,?,?,?,?)";
			pstm = con.prepareCall(sql);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getDescripcion());
			pstm.setDouble(3, p.getPrecioUnitario());
			pstm.setInt(4, p.getStock());
			pstm.setInt(5, p.getTipoProducto().getIdTipoProducto());
			pstm.setInt(6, p.getProveedor().getIdproveedor());

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
				e2.printStackTrace();
			}
		}
		return salida;
	}
	public Producto buscarProducto(int id) {
		Producto obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.obtenerConexion();
			String sql = "select *from producto where idProducto=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				obj = new Producto();
				obj.setIdProducto((rs.getInt(1)));;
				obj.setNombre((rs.getString(2)));;
				obj.setDescripcion((rs.getString(3)));;
				obj.setDescripcion((rs.getString(4)));;
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
	
	public int actualizaProducto(Producto p) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "update producto set  nombre=?,descripcion=?,precioUnitario=?,stock=?,idProveedor=?,idTipoProducto=? where idProducto=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getDescripcion());
			pstm.setDouble(3, p.getPrecioUnitario());
			pstm.setInt(4, p.getStock());
			pstm.setInt(5, p.getTipoProducto().getIdTipoProducto());
			pstm.setInt(6, p.getProveedor().getIdproveedor());
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

	public int eliminaProducto(int idproducto) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "delete from producto where idProducto=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idproducto);

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
