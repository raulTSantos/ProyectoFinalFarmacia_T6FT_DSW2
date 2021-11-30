package com.cibertec.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
