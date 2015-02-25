package cinema.beans;


import java.util.List;
import java.util.Calendar;
import java.util.Date;
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
import cinema.model.Reservations;

@Stateless
@Path("/reservations")
public class ReservationsBean {
	
	@PersistenceContext
	private EntityManager em;
	
	@GET
	@Produces("application/json")
	public List<Reservations> getAllReservations(){
		return em.createNamedQuery("getAllReservations", Reservations.class).getResultList();
	}
	
	@GET
	@Path("/15")
	@Produces
	public Reservations getReservations15minBeforeCurDate(){
		Date n = new Date();
		Date newDate = new Date(n.getTime() - 900000L);
		
		TypedQuery<Reservations> q = em.createNamedQuery("getReservationsFromCurDate", Reservations.class);
		q.setParameter("dateReservation", newDate);
		return q.getSingleResult();
	}
	
	
	@GET
	@Path("/{user}")
	@Produces("application/json")
	public List<Reservations> getReservationsByUser(@PathParam("user") String user ){
		TypedQuery<Reservations> query = em.createNamedQuery("getReservationsByUser", Reservations.class);
		query.setParameter("user", user);
		return query.getResultList();
	}
	
	// ///////////////////////////////
	@GET
	@Path("/{dateReservation}")
	@Produces("application/json")
	public List<Reservations> getReservationsFromCurDate(@PathParam("dateReservation") Date dateReservation) {
		TypedQuery<Reservations> q = em.createNamedQuery("getReservationsFromCurDate", Reservations.class);
		q.setParameter("dateReservation", dateReservation);
		return q.getResultList();
	}
	
	@GET
	@Path("/{allProjections}")
	@Produces("application/json")
	public List<Reservations> getReservationsForProjection(@PathParam("allProjectionss") AllProjections projection){
		TypedQuery<Reservations> q = em.createNamedQuery("getReservationsForProjection", Reservations.class);
		q.setParameter("allProjectionss", projection);
		return q.getResultList();
		
	}
	
	
	@POST
	@Consumes("application/json")
	public void addReservation(Reservations reservation) {
		if(reservation != null) 
			em.persist(reservation);
	}
	
	
	
}
