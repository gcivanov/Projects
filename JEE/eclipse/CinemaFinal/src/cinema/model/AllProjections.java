package cinema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@NamedQueries({
		@NamedQuery(name = "getAllProjections", query = "SELECT a FROM AllProjections a"),
		@NamedQuery(name = "getProjectionsById", query = "SELECT s FROM AllProjections s WHERE s.id = :id")
		
})
@XmlRootElement
public class AllProjections implements Serializable {
	
	private static final long serialVersionUID = -4390718857288998062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="TIMES_OF_PROJ" , nullable = false)
	private String timesOfProj;
	
	
	
	
//	@OneToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "MOVIES_ID")
	@OneToOne( cascade = CascadeType.PERSIST)
	private Movies movie;  ////
	
//	@OneToMany(mappedBy = "allProjection", cascade = CascadeType.PERSIST)
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Rooms> rooms; /////////

	
	@OneToMany(mappedBy= "allProjectionss", cascade = CascadeType.PERSIST)
	private List<Reservations> reservation; ////

	
	public AllProjections(){
		rooms = new ArrayList<Rooms>();
		reservation = new ArrayList<Reservations>();
		
	}
	public void addRoom(Rooms r) {
		rooms.add(r);
	}
	
	
	public AllProjections(Movies movie, List<Rooms> rooms, String timesOfProj ){
//		if(movie != null && rooms != null && timesOfProj != null) {
			this.movie = movie;
			this.rooms = rooms;
			this.timesOfProj = timesOfProj;
//		}
	}
	
	
	
	
	
	
	
	
	

	
	
	public void setId(long id) {
		this.id = id;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public List<Rooms> getRooms() {
		return rooms;
	}
	public void setRooms(List<Rooms> rooms) {
		this.rooms = rooms;
	}
	public List<Reservations> getReservation() {
		return reservation;
	}
	public void setReservation(List<Reservations> reservation) {
		this.reservation = reservation;
	}
	public long getId() {
		return id;
	}
	public String getTimesOfProj() {
		return timesOfProj;
	}
	public void setTimesOfProj(String timesOfProj) {
		this.timesOfProj = timesOfProj;
	}
	@Override
	public String toString() {
		return "AllProjections [id=" + id + ", timesOfProj=" + timesOfProj
				+ ", movie=" + movie + ", rooms=" + rooms + ", reservation="
				+ reservation + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result
				+ ((reservation == null) ? 0 : reservation.hashCode());
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
		result = prime * result
				+ ((timesOfProj == null) ? 0 : timesOfProj.hashCode());
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
		AllProjections other = (AllProjections) obj;
		if (id != other.id)
			return false;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (reservation == null) {
			if (other.reservation != null)
				return false;
		} else if (!reservation.equals(other.reservation))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		if (timesOfProj == null) {
			if (other.timesOfProj != null)
				return false;
		} else if (!timesOfProj.equals(other.timesOfProj))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
