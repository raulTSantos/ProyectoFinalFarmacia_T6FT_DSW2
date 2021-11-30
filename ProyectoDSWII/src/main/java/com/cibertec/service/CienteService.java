package com.cibertec.service;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cibertec.dao.ClienteDAO;
import com.cibertec.entity.Cliente;

public class CienteService {
	private ClienteDAO daoCliente = new ClienteDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCliente() {

		return Response.ok(daoCliente.listaCliente()).build();
	}

	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertarCliente(Cliente obj) {
		if (obj == null) {
			return Response.status(400).entity("Por favor, ingrese los datos de la persona").build();
		}
		return Response.ok(daoCliente.insertaCliente(obj)).build();
	}

	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarCliente(Cliente obj) {
		Cliente salida = null;
		int nro = 0;
		salida = daoCliente.buscarCliente(obj.getIdCliente());
		if (salida == null) {
			return Response.status(400).entity("No se puede ubicar al cliente").build();
		} else {
			nro = daoCliente.actualizaCliente(obj);
			if (nro > 0) {
				return Response.ok().entity("SE actualizo").build();
			}
			return Response.status(400).entity("Surgio un error").build();
		}

	}

	@DELETE
	@Path("/eliminar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(@PathParam("id") int id) {
		Cliente obj = null;
		obj = daoCliente.buscarCliente(id);
		if (obj == null) {
			return Response.status(Status.BAD_REQUEST).entity("Cliente not found").build();
		}else {
			return Response.ok(daoCliente.eliminaCliente(id)).entity("Se Elimino").build();
		}

	}
}
