package cinema.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cinema.model.AllProjections;
import cinema.model.Movies;
import cinema.model.Reservations;
import cinema.model.Rooms;
import cinema.model.Users;

@Singleton
@Startup
public class AddFirstAdminBean {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private UsersBean ub;
	
	
	@PostConstruct
	public void addElement(){
		
		
		
//		dropAllTables();
		
		addElements();
		
		
		
		
		
//		Reservations reserve1 = new Reservations(null,null, new Date(),"20");
//		em.persist(reserve1);
	
	}
	
	private void addElements() {
		if(ub.getAdmins().size() == 0) {
			Users adminU = new Users("admin","admin","null");
			adminU.setAdmin(true);
			
			Rooms room1 = new Rooms("1", 5);
			Rooms room2 = new Rooms("2",10);
			List<Rooms> rooms = new ArrayList<Rooms>();
			
			rooms.add(room1);
			rooms.add(room2);
			
			String[] times = {"12:00","11:30","9:30"};
			
			em.persist(adminU);
			em.persist(room1);
			em.persist(room2);
			
			
//			AllProjections project1 = new AllProjections(new Movies("Lord of the Rings1", 2001), rooms, times);
//			AllProjections project2 = new AllProjections(new Movies("Lord of the Rings2", 2002), rooms, times);
//			AllProjections project3 = new AllProjections(new Movies("Lord of the Rings2", 2003), rooms, times);
			
			AllProjections project1 = new AllProjections(new Movies("Lord of the Rings1", 2001), rooms, "12:20,15:20");
			AllProjections project2 = new AllProjections(new Movies("Lord of the Rings2", 2002), rooms, "12:20,11:30");
			AllProjections project3 = new AllProjections(new Movies("Lord of the Rings2", 2003), rooms, "12:20");
			
//			ArrayList<String> ar = new ArrayList<>();
//			ar.add("12:20");
//			ar.add("3:20");
//			
//			AllProjections project1 = new AllProjections(new Movies("Lord of the Rings1", 2001), rooms, ar);
//			AllProjections project2 = new AllProjections(new Movies("Lord of the Rings2", 2002), rooms, ar);
//			AllProjections project3 = new AllProjections(new Movies("Lord of the Rings2", 2003), rooms, ar);
			
			
			
			Reservations reserve1 = new Reservations(adminU, project1, new Date(),"20");
			
			
			em.persist(project1);
			em.persist(project2);
			em.persist(project3);
			em.persist(reserve1);
		}
	}
	
	private void dropAllTables(){
	    Query q1 = em.createNativeQuery("DROP TABLE AllProjections");
		Query q2 = em.createNativeQuery("DROP TABLE Movies");
		Query q3 =em.createNativeQuery("DROP TABLE Reservations");
		Query q4 =em.createNativeQuery("DROP TABLE Users");
		Query q5 = em.createNativeQuery("DROP TABLE Rooms");
		
		q1.executeUpdate();
		q2.executeUpdate();
		q3.executeUpdate();
		q4.executeUpdate();
		q5.executeUpdate();
	}
	
}
