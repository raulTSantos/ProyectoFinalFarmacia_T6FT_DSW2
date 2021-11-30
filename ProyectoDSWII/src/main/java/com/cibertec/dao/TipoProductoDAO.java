package com.cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.entity.TipoProducto;
import com.cibertec.util.MysqlConexion;

public class TipoProductoDAO {

	public List<TipoProducto> listaTipoProducto() {
		ArrayList<TipoProducto> data = new ArrayList<TipoProducto>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "select * from tipoProducto";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			TipoProducto c = null;
			while (rs.next()) {
				c = new TipoProducto();
				c.setIdTipoProducto(rs.getInt(1));
				c.setNombre(rs.getString(2));
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
}
