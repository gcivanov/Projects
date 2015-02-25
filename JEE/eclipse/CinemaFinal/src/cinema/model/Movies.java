package cinema.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@NamedQueries({
	@NamedQuery(name = "getAllMovies" , query = "SELECT m FROM Movies m")
})
@XmlRootElement
public class Movies implements Serializable {
	
	private static final long serialVersionUID = 8797872986654688666L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MOVIES_ID")
	private long id;

	@Column(name = "NAME" , nullable = false)
	private String name;
	
	private int year;

	
//	@OneToOne(mappedBy= "movie", cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "PROJECTION_ID")
	@OneToOne(cascade = CascadeType.PERSIST)
	private AllProjections allProjections; /////
	
	
	public Movies(){
	}
	
	public Movies(String name , int year ){
	
//		if(name != null && year > 0 && year / 10000 == 0) {
			this.name = name;
			this.year = year;
//		}
	}
	
	
	public long getId() {
		return id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
//	public AllProjections getAllProjections() {
//		return allProjections;
//	}
//
//	public void setAllProjections(AllProjections allProjections) {
//		this.allProjections = allProjections;
//	}

	public AllProjections getAllProjections() {
		return allProjections;
	}

	public void setAllProjections(AllProjections allProjections) {
		this.allProjections = allProjections;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Movies [id=" + id + ", name=" + name + ", year=" + year
				+ ", allProjections=" + allProjections + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allProjections == null) ? 0 : allProjections.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
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
		Movies other = (Movies) obj;
		if (allProjections == null) {
			if (other.allProjections != null)
				return false;
		} else if (!allProjections.equals(other.allProjections))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	
	
	
	
	
	
}
