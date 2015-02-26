package com.example.cinema.model;

import java.io.Serializable;
import java.util.List;


public class Users implements Serializable {
	
	
	private static final long serialVersionUID = 2160829926824657188L;

	private long id;
	
	private String name;
	
	private String pass;
	
	private String email;
	
	private boolean isAdmin;
	
	private List<Reservations> reservations;
	
	
	
	public Users(){
	}
	
	public Users(String name , String pass , String email ) {
		if(name != null && pass != null) {
			this.name = name;
			this.pass = pass;
		}
		this.email = email;
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

}
