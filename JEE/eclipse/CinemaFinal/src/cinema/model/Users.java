package cinema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@NamedQueries({
	@NamedQuery(name = "getRegularUsers", query = "SELECT u FROM Users u WHERE u.isAdmin = FALSE"),
	@NamedQuery(name = "getAdmins", query = "SELECT u FROM Users u WHERE u.isAdmin = TRUE" ),
	@NamedQuery(name = "getUserByName", query = "SELECT u FROM Users u WHERE u.name = :name")
})


@XmlRootElement
public class Users implements Serializable {
	
	
	private static final long serialVersionUID = 2160829926824657188L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME")//, unique = true , nullable = false)
	private String name;
	
	private String pass;
	
	@Column(name = "EMAIL")// , unique = true , nullable = false)
	private String email;
	
	private boolean isAdmin;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
	private List<Reservations> reservations;
	
	
	
	public Users(){
	}
	
	public Users(String name , String pass , String email ) {
		if(name != null && pass != null) {
			this.name = name;
			this.pass = pass;
		}
		this.email = email;
		reservations = new ArrayList<Reservations>();
	
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Reservations> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservations> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", pass=" + pass
				+ ", email=" + email + ", isAdmin=" + isAdmin
				+ ", reservations=" + reservations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result
				+ ((reservations == null) ? 0 : reservations.hashCode());
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
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (reservations == null) {
			if (other.reservations != null)
				return false;
		} else if (!reservations.equals(other.reservations))
			return false;
		return true;
	}
	
	
	
	

}
