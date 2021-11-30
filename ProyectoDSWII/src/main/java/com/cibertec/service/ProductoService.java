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

import com.cibertec.dao.ProductoDAO;
import com.cibertec.entity.Producto;

@Path("productos")
public class ProductoService {

	private ProductoDAO daoProducto= new ProductoDAO();

	@GET
	@Path("/listaProductoPorNombre/{filtro}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listaProductoPorNombre(@PathParam("filtro") String filtro) {

		return Response.ok(daoProducto.listarProductoPorNombre(filtro)).build();
	}
	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertarProducto(Producto obj) {
		if (obj == null) {
			return Response.status(400).entity("Por favor, ingrese los datos del producto").build();
		}
		return Response.ok(daoProducto.insertaProducto(obj)).build();
	}
	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarProducto(Producto obj) {
		Producto salida = null;
		int nro = 0;
		salida = daoProducto.buscarProducto(obj.getIdProducto());
		if (salida == null) {
			return Response.status(400).entity("No se puede ubicar al proveedor").build();
		} else {
			nro = daoProducto.actualizaProducto(obj);
			if (nro > 0) {
				return Response.ok().entity("SE actualizo").build();
			}
			return Response.status(400).entity("Surgio un errror").build();
		}

	}

	@DELETE
	@Path("/eliminar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProducto(@PathParam("id") int id) {
		Producto obj = null;
		obj = daoProducto.buscarProducto(id);
		if (obj == null) {
			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
		}else {
			//daoProveedor.eliminaProveedor(id);
			return Response.ok(daoProducto.eliminaProducto(id)).entity("Se Elimino").build();
		}
		//return Response.ok().entity(obj).build();
	}
	
}
