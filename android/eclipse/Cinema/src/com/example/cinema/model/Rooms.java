package com.example.cinema.model;

import java.io.Serializable;


public class Rooms implements Serializable {
	

	private static final long serialVersionUID = 8656946217327123854L;

	private long id;
	
	private String name;
	
	private int capacity;
	
	private AllProjections allProjection; ////
	
	public Rooms(){
//		super();
	}
	public Rooms(String name , int capacity) {
		if(name != null && capacity > 0) {
			this.name = name;
			this.capacity = capacity;
		}
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
	public AllProjections getAllProjection() {
		return allProjection;
	}
	public void setAllProjection(AllProjections allProjection) {
		this.allProjection = allProjection;
	}
	@Override
	public String toString() {
		return "Rooms [id=" + id + ", name=" + name + ", capacity=" + capacity
				+ ", allProjection=" + allProjection + "]";
	}

	
	
	
}
