package com.cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.entity.Pago;
import com.cibertec.util.MysqlConexion;

public class PagoDAO {

	public List<Pago> listaPago() {
		ArrayList<Pago> data = new ArrayList<Pago>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "select * from pago";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			Pago c = null;
			while (rs.next()) {
				c = new Pago();
				c.setIdPago(rs.getInt(1));
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
