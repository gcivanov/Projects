package cinema.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cinema.model.Rooms;


@Stateless
@Path("/rooms")
public class RoomsBean {

	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces("application/json")
	public List<Rooms> getAllRooms(){
		return em.createNamedQuery("getAllRooms", Rooms.class).getResultList();
	}
	
}
