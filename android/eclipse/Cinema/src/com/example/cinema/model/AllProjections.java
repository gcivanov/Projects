package com.example.cinema.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


public class AllProjections implements Serializable {
	
	private static final long serialVersionUID = -4390718857288998062L;


	private long id;
	
	private String[] timesOfProj;
	
	private Movies movie;  ////
	
	private List<Rooms> rooms; /////////
	
	private List<Reservations> reservation; ////

	
	public AllProjections(){
	}
	
	public AllProjections(Movies movie, List<Rooms> rooms, String[] timesOfProj ){
		if(movie != null && rooms != null && timesOfProj != null) {
			this.movie = movie;
			this.rooms = rooms;
			this.timesOfProj = timesOfProj;
		}
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String[] getTimesOfProj() {
		return timesOfProj;
	}

	public void setTimesOfProj(String[] timesOfProj) {
		this.timesOfProj = timesOfProj;
	}
	

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	

	public List<Reservations> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservations> reservation) {
		this.reservation = reservation;
	}

	public List<Rooms> getRooms() {
		return rooms;
	}

	public void setRooms(List<Rooms> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "AllProjections [id=" + id + ", timesOfProj="
				+ Arrays.toString(timesOfProj) + ", movie=" + movie
				+ ", rooms=" + rooms + ", reservation=" + reservation + "]";
	}
	
	
	
}
