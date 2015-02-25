package cinema.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cinema.model.Movies;

@Stateless
@Path("/movies")
public class MoviesBean {
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces("application/json")
	public List<Movies> getAllMovies() {
		return em.createNamedQuery("getAllMovies", Movies.class).getResultList();
	}
	
}