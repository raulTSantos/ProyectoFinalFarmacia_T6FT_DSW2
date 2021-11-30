package com.cibertec.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cibertec.dao.VentaDAO;
import com.cibertec.entity.Venta;

@Path("ventas")
public class VentaService {

	private VentaDAO daoVenta= new VentaDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarVenta() {

		return Response.ok(daoVenta.listaPedido()).build();
	}
	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertarVenta(Venta obj) {
		if (obj == null) {
			return Response.status(400).entity("Por favor, ingrese un regoistro").build();
		}
		return Response.ok(daoVenta.inserta(obj)).build();
	}
}
