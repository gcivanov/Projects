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

import cinema.model.AllProjections;
import cinema.model.Rooms;

@Stateless
@Path("/projections")
public class AllProjectionsBeans {
	@PersistenceContext
	private EntityManager em;
	
	
	@GET
	@Produces("application/json")
	public List<AllProjections> getAllProjections(){
		List<AllProjections> list = em.createNamedQuery("getAllProjections", AllProjections.class).getResultList();
		for(AllProjections all : list) {
			System.out.println();
			System.out.println();
			
			if(!all.getRooms().isEmpty())
				System.out.println("   -==" + all.getId() + " ++ ");//+ all.getRooms().get(0).getId());
			else
				System.out.println("   -==" + all.getId() + " +NO+ ");
			
			System.out.println();
			System.out.println();
		}
		
		return list;
	}
	
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public AllProjections getProjectionById(@PathParam("id") long id) {
    	TypedQuery<AllProjections> query = em.createNamedQuery("getProjectionsById", AllProjections.class);
    	query.setParameter("id", id);
    	return query.getSingleResult();
    }
	
    @POST
    @Consumes("application/json")
    public void addProjection(AllProjections proj){
    	if(proj != null)
    		em.persist(proj);
    	
    }
	
}
