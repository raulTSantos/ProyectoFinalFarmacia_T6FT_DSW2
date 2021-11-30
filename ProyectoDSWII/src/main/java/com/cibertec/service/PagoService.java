package com.cibertec.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cibertec.dao.PagoDAO;

@Path("pagos")
public class PagoService {
	private PagoDAO daoPago = new PagoDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPago() {

		return Response.ok(daoPago.listaPago()).build();
	}
}
