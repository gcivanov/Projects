package cinema.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cinema.model.Users;

@Stateless
@Path("/users")
public class UsersBean {
	
	@PersistenceContext
	private EntityManager em;
	
	@GET
	@Produces("application/json")
	public List<Users> getUsers(){
		return em.createNamedQuery("getAdmins", Users.class).getResultList();
	}
	
	@GET
	@Path("/admins")
	@Produces("application/json")
	public List<Users> getAdmins(){
		return em.createNamedQuery("getAdmins", Users.class).getResultList();
	}
	
	@GET
	@Path("/{name}")
	@Produces("application/json") 
	public Users getUserByName(@PathParam("name") String name) {
		TypedQuery<Users> query = em.createNamedQuery("getUserByName", Users.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}
	
	
	@POST
	@Consumes("application/json")
	public void addUser(Users user){
		if(user != null)
			em.persist(user);
	}
}
