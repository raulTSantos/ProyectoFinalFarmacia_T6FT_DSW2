package com.cibertec.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cibertec.dao.VentaDAO;

@Path("ventas")
public class VentaService {

	private VentaDAO daoVenta= new VentaDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarVenta() {

		return Response.ok(daoVenta.listaPedido()).build();
	}
	
}
