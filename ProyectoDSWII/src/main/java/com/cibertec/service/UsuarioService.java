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

import com.cibertec.dao.UsuarioDAO;
import com.cibertec.entity.Usuario;


@Path("usuarios")
public class UsuarioService {

	private UsuarioDAO daoUser = new UsuarioDAO();

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/json")
	public Response logeo( Usuario obj) {
		Usuario persona = null;
		String pass = obj.getLogin();
		String lo =obj.getPassword();
		if(pass !=null && lo != null) {
			persona= daoUser.valida(obj);
		}
		return Response.ok().entity(persona).build();
	}
	
	@GET
	@Path("/lista")
	@Produces("application/json")
	public Response listaUsuer() {
		
		return Response.ok(daoUser.listarTodos()).build();
	}

	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarProveedor(Usuario obj) {
		Usuario salida = null;
		int nro = 0;
		salida = daoUser.buscarUsuario(obj.getIdUsuario());
		if (salida == null) {
			return Response.status(400).entity("No se puede ubicar al proveedor").build();
		} else {
			nro = daoUser.actualizaUsuario(obj);
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
		Usuario obj = null;
		obj = daoUser.buscarUsuario(id);
		if (obj == null) {
			return Response.status(Status.BAD_REQUEST).entity("User not found").build();
		}else {
			//daoProveedor.eliminaProveedor(id);
			return Response.ok(daoUser.eliminaProveedor(id)).entity("Se Elimino").build();
		}
		//return Response.ok().entity(obj).build();
	}
	
	

	@GET
	@Path("/login/{user}/{pass}")
	@Produces("application/json")
	public Response login(@PathParam("user") String log, @PathParam("pass")String pas) {
		
		return Response.ok(daoUser.login(log, pas) ).build();
	}
	
}
