package cinema.model;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@NamedQueries({
	@NamedQuery(name = "getAllRooms" , query = "SELECT r FROM Rooms r")
})
@XmlRootElement
public class Rooms implements Serializable {
	

	private static final long serialVersionUID = 8656946217327123854L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "NAME" , nullable = false)
	private String name;
	
	private int capacity;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "AllProjections_ID")
	@ManyToMany(mappedBy = "rooms", cascade = CascadeType.PERSIST)
	private List<AllProjections> allProjection; ////
	
	public Rooms(){
//		super();
	}
	public Rooms(String name , int capacity) {
//		if(name != null && capacity > 0) {
			this.name = name;
			this.capacity = capacity;
//		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<AllProjections> getAllProjection() {
		return allProjection;
	}
	public void setAllProjection(List<AllProjections> allProjection) {
		this.allProjection = allProjection;
	}
	@Override
	public String toString() {
		return "Rooms [id=" + id + ", name=" + name + ", capacity=" + capacity
				+ ", allProjection=" + allProjection + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allProjection == null) ? 0 : allProjection.hashCode());
		result = prime * result + capacity;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Rooms other = (Rooms) obj;
		if (allProjection == null) {
			if (other.allProjection != null)
				return false;
		} else if (!allProjection.equals(other.allProjection))
			return false;
		if (capacity != other.capacity)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
	

	
	
	
}
