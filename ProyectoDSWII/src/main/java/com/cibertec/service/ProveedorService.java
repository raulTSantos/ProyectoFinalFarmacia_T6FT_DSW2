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

import com.cibertec.dao.ProveedorDAO;
import com.cibertec.entity.Proveedor;

@Path("proveedores")
public class ProveedorService {
	private ProveedorDAO daoProveedor = new ProveedorDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProveedor() {

		return Response.ok(daoProveedor.listaProveedor()).build();
	}

	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertarProveedor(Proveedor obj) {
		if (obj == null) {
			return Response.status(400).entity("Por favor, ingrese los datos de la persona").build();
		}
		return Response.ok(daoProveedor.insertaProveedor(obj)).build();
	}

	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarProveedor(Proveedor obj) {
		Proveedor salida = null;
		int nro = 0;
		salida = daoProveedor.buscarProveedor(obj.getIdproveedor());
		if (salida == null) {
			return Response.status(400).entity("No se puede ubicar al proveedor").build();
		} else {
			nro = daoProveedor.actualizaProveedor(obj);
			if (nro > 0) {
				return Response.ok().entity("SE actualizo").build();
			}
			return Response.status(400).entity("Surgio un errror").build();
		}

	}

	@DELETE
	@Path("/eliminar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePersona(@PathParam("id") int id) {
		Proveedor obj = null;
		obj = daoProveedor.buscarProveedor(id);
		if (obj == null) {
			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
		}else {
			
			return Response.ok(daoProveedor.eliminaProveedor(id)).entity("Se Elimino").build();
		}
	}

}
