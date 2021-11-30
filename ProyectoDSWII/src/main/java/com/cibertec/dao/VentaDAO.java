package com.cibertec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cibertec.entity.Cliente;
import com.cibertec.entity.DetalleVenta;
import com.cibertec.entity.Pago;
import com.cibertec.entity.Usuario;
import com.cibertec.entity.Venta;
import com.cibertec.util.MysqlConexion;

public class VentaDAO {

	public int inserta(Venta pedido) {

		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2 = null, pstm3 = null;
		try {
			conn = MysqlConexion.obtenerConexion();

			conn.setAutoCommit(false);
			String sql1 = "insert into venta values(null,?,?,?)";
			pstm1 = conn.prepareStatement(sql1);
			
			pstm1.setInt(1, pedido.getCliente().getIdCliente());
			pstm1.setInt(2, pedido.getUsuario().getIdUsuario());
			pstm1.setInt(3, pedido.getPago().getIdPago());
			pstm1.executeUpdate();

			String sql2 = "select max(idVenta) from venta";
			pstm2 = conn.prepareStatement(sql2);
			ResultSet rs = pstm2.executeQuery();
			rs.next();
			int idPedido = rs.getInt(1);

			String sql3 = "insert into detalleVenta values(?,?,?,?)";
			pstm3 = conn.prepareStatement(sql3);
			for (DetalleVenta aux : pedido.getDetalleVenta()) {
				pstm3.setInt(1, idPedido);
				pstm3.setInt(2, aux.getIdProducto());
				pstm3.setDouble(3, aux.getPrecio());
				pstm3.setInt(4, aux.getCantidad());
				pstm3.setDouble(5, aux.getDescuento());
				pstm3.executeUpdate();

			}

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm1.close();
				pstm2.close();
				pstm3.close();

			} catch (SQLException e) {
			}
		}
		return contador;
	}

	public ArrayList<Venta> listaPedidoPorId(int idPedido) {

		ArrayList<Venta> lista = new ArrayList<Venta>();

		Connection conn = null;
		PreparedStatement pstm = null, pstm1 = null;
		ResultSet rs = null, rs1 = null;
		ArrayList<DetalleVenta> listaDetalle = null;
		try {
			conn = MysqlConexion.obtenerConexion();

			String sql = "select c.idcliente,c.nombre,c.apellido,p.idVenta,p.fechaRegistro,p.idusuario from venta p join cliente c on p.idcliente=c.idcliente join Pago pa on p.idPago=pa.idPago where p.idVenta = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idPedido);
			rs = pstm.executeQuery();

			String sql1 = "SELECT * FROM detalleVenta where idVenta=?";
			pstm1 = conn.prepareStatement(sql1);

			Venta obj = null;
			DetalleVenta objDetalle = null;
			Cliente objCli = null;
			Pago objPago = null;
			Usuario objUsu = null;
			while (rs.next()) {
				objCli = new Cliente();
				objCli.setIdCliente(rs.getInt(1));
				objCli.setNombre(rs.getString(2));
				objCli.setApellido(rs.getString(3));

				objUsu = new Usuario();
				objUsu.setIdUsuario(rs.getInt(10));

				obj = new Venta();
				obj.setCliente(objCli);
				
				obj.setIdVenta(rs.getInt(4));
				obj.setFechaRegistro(rs.getDate(5));
				obj.setPago(objPago);
				obj.setUsuario(objUsu);

				pstm1.setInt(1, rs.getInt(4));

				rs1 = pstm1.executeQuery();

				listaDetalle = new ArrayList<DetalleVenta>();
				while (rs1.next()) {
					objDetalle = new DetalleVenta();
					objDetalle.setIdVenta(rs1.getInt(1));
					objDetalle.setIdProducto(rs1.getInt(2));
					objDetalle.setPrecio(rs1.getDouble(3));
					objDetalle.setCantidad(rs1.getInt(4));
					objDetalle.setDescuento(rs1.getInt(5));
					listaDetalle.add(objDetalle);
					obj.setDetalleVenta(listaDetalle);
				}

				lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (rs1 != null)
					rs1.close();
				if (pstm1 != null)
					pstm1.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return lista;
	}

	public ArrayList<Venta> listaPedido() {
		ArrayList<Venta> lista = new ArrayList<Venta>();

		Connection conn = null;
		PreparedStatement pstm = null, pstm1 = null;
		ResultSet rs = null, rs1 = null;
		ArrayList<DetalleVenta> listaDetalle = null;
		try {
			conn = MysqlConexion.obtenerConexion();
			String sql = "select c.idcliente,c.nombre,c.apellido,p.idVenta,p.fechaRegistro,p.idusuario,pa.id_pago from venta p join cliente c on p.idcliente=c.idcliente join TB_PAGO pa on p.id_pago=pa.id_pago";
			pstm = conn.prepareStatement(sql);


			String sql1 = "SELECT * FROM detalleVenta where idVenta=?";
			pstm1 = conn.prepareStatement(sql1);

			rs = pstm.executeQuery();

			Venta obj = null;
			DetalleVenta objDetalle = null;
			Pago objPago = null;
			Cliente objCli = null;
			Usuario objUsu = null;
			while (rs.next()) {
				objCli = new Cliente();
				objCli.setIdCliente(rs.getInt(1));
				objCli.setNombre(rs.getString(2));
				objCli.setApellido(rs.getString(3));

				objPago = new Pago();
				objPago.setIdPago(rs.getInt(7));
				

				objUsu = new Usuario();
				objUsu.setIdUsuario(rs.getInt(6));
				

				obj = new Venta();
				obj.setCliente(objCli);
				obj.setIdVenta(rs.getInt(4));
				obj.setFechaRegistro(rs.getDate(5));

				
				obj.setUsuario(objUsu);
				obj.setPago(objPago);

				pstm1.setInt(1, rs.getInt(4));

				rs1 = pstm1.executeQuery();

				listaDetalle = new ArrayList<DetalleVenta>();
				while (rs1.next()) {
					objDetalle = new DetalleVenta();
					objDetalle.setIdVenta(rs1.getInt(1));
					objDetalle.setIdProducto(rs1.getInt(2));
					objDetalle.setPrecio(rs1.getDouble(3));
					objDetalle.setCantidad(rs1.getInt(4));
					objDetalle.setDescuento(rs1.getInt(5));		
					listaDetalle.add(objDetalle);
					obj.setDetalleVenta(listaDetalle);
				}

				lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (rs1 != null)
					rs1.close();
				if (pstm1 != null)
					pstm1.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return lista;
	}

	public ArrayList<Venta> listaPedidoporCliente(int idCliente) {

		ArrayList<Venta> lista = new ArrayList<Venta>();

		Connection conn = null;
		PreparedStatement pstm = null, pstm1 = null;
		ResultSet rs = null, rs1 = null;
		ArrayList<DetalleVenta> listaDetalle = null;
		try {
			conn = MysqlConexion.obtenerConexion();

			String sql = "select c.idcliente,c.nombres,c.apellidos,p.idpedido,p.fechaRegistro,p.fechaEntrega,p.lugarEntrega,p.estado,u.idubigeo,p.idusuario from pedido p join cliente c on p.idcliente=c.idcliente join ubigeo u on p.idubigeo=u.idubigeo where p.idcliente = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idCliente);

			String sql1 = "SELECT * FROM detalleVenta where idVenta=?";
			pstm1 = conn.prepareStatement(sql1);

			rs = pstm.executeQuery();

			Venta obj = null;
			DetalleVenta objDetalle = null;
			Pago objPago = null;
			Cliente objCli = null;
			Usuario objUsu = null;
			while (rs.next()) {
				objCli = new Cliente();
				objCli.setIdCliente(rs.getInt(1));
				objCli.setNombre(rs.getString(2));
				objCli.setApellido(rs.getString(3));


				objUsu = new Usuario();
				objUsu.setIdUsuario(rs.getInt(10));

				obj = new Venta();
				obj.setCliente(objCli);
				obj.setIdVenta(rs.getInt(4));
				obj.setFechaRegistro(rs.getDate(5));
				obj.setFechaRegistro(rs.getDate(6));
				obj.setPago(objPago);
				obj.setUsuario(objUsu);

				pstm1.setInt(1, rs.getInt(4));
				rs1 = pstm1.executeQuery();

				listaDetalle = new ArrayList<DetalleVenta>();
				while (rs1.next()) {
					objDetalle = new DetalleVenta();
					objDetalle.setIdVenta(rs1.getInt(1));
					objDetalle.setIdProducto(rs1.getInt(2));
					objDetalle.setPrecio(rs1.getDouble(3));
					objDetalle.setCantidad(rs1.getInt(4));
					objDetalle.setDescuento(rs1.getInt(5));
					listaDetalle.add(objDetalle);
					obj.setDetalleVenta(listaDetalle);
				}

				lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (rs1 != null)
					rs1.close();
				if (pstm1 != null)
					pstm1.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return lista;
	}
}
