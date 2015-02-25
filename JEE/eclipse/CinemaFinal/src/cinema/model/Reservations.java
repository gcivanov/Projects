package cinema.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

	
@Entity
@NamedQueries({
	@NamedQuery(name = "getReservationsByUser" , query = "SELECT r FROM Reservations r WHERE r.user = :user"),
	@NamedQuery(name = "getAllReservations", query = "SELECT r FROM Reservations r"),
	@NamedQuery(name = "getReservationsFromCurDate", query = "SELECT r FROM Reservations r WHERE r.dateReservation > :dateReservation"), //or CONVERT(DATETYPE, date) ....
	@NamedQuery(name = "getReservationsForProjection", query = "SELECT r FROM Reservations r WHERE r.allProjectionss = :allProjectionss")
})
@XmlRootElement
public class Reservations implements Serializable {

	
	private static final long serialVersionUID = -2663487377307587268L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESERVATIONS_ID")
	private long id;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "User")
	private Users user;  /////

	
//	@JoinColumn(name = "allProjections")
	@ManyToOne( cascade = CascadeType.PERSIST)
	private AllProjections allProjectionss; ////
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@NotNull
	private Date dateReservation;
	
	private String busySeats;

	
	public Reservations(){
	}

	

	public Reservations(Users user, AllProjections allProjections,
			Date dateReservation, String busySeats) {
		
		this.user = user;
		this.allProjectionss = allProjections;
		this.dateReservation = dateReservation;
		this.busySeats = busySeats;
	}

	
	



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public AllProjections getAllProjectionss() {
		return allProjectionss;
	}



	public void setAllProjectionss(AllProjections allProjectionss) {
		this.allProjectionss = allProjectionss;
	}



	public Date getDateReservation() {
		return dateReservation;
	}



	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}



	public String getBusySeats() {
		return busySeats;
	}



	public void setBusySeats(String busySeats) {
		this.busySeats = busySeats;
	}



	@Override
	public String toString() {
		return "Reservations [id=" + id + ", user=" + user
				+ ", allProjectionss=" + allProjectionss + ", dateReservation="
				+ dateReservation + ", busySeats=" + busySeats + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allProjectionss == null) ? 0 : allProjectionss.hashCode());
		result = prime * result
				+ ((busySeats == null) ? 0 : busySeats.hashCode());
		result = prime * result
				+ ((dateReservation == null) ? 0 : dateReservation.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservations other = (Reservations) obj;
		if (allProjectionss == null) {
			if (other.allProjectionss != null)
				return false;
		} else if (!allProjectionss.equals(other.allProjectionss))
			return false;
		if (busySeats == null) {
			if (other.busySeats != null)
				return false;
		} else if (!busySeats.equals(other.busySeats))
			return false;
		if (dateReservation == null) {
			if (other.dateReservation != null)
				return false;
		} else if (!dateReservation.equals(other.dateReservation))
			return false;
		if (id != other.id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	



	
	
	
//	public static void main(String[] args) {
//		
//		System.out.println("   123   "+ 2014 / 10000);
//		
//		dateReservation = new Date();
//
//		System.out.println("" + dateReservation + " \n"  );
//		
//		
//		
//		Thread th = new Thread() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Date second = new Date();
//				System.out.println(second);
//				System.out.println(second.compareTo(dateReservation));
//				System.out.println(second.compareTo(second));
//				System.out.println(dateReservation.compareTo(second));
//				
//			}
//			
//			
//		};
//		th.start();
//		
//		Date n = new Date();
//		Calendar cal = Calendar.getInstance();
//		Date newDate = new Date(n.getTime() - 900000L); // 7 * 24 * 60 * 60 * 1000
//		
////		Calendar calendar = Calendar.getInstance();
////		calendar.setTime(n);
////		calendar.add(Calendar.DAY_OF_YEAR, -7);
////		Date newDate = calendar.getTime();
////		
//		System.out.println("   new1  " + newDate + "     old  " + n);
//	}
	
}
