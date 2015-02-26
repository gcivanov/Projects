package com.example.cinema.model;

import java.io.Serializable;



public class Movies implements Serializable {
	
	private static final long serialVersionUID = 8797872986654688666L;


	private long id;

	private String name;
	
	private int year;

	private AllProjections allProjections; /////
	
	
	public Movies(){
	}
	
	public Movies(String name , int year ){
	
		if(name != null && year > 0 && year / 10000 == 0) {
			this.name = name;
			this.year = year;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public AllProjections getAllProjections() {
		return allProjections;
	}

	public void setAllProjections(AllProjections allProjections) {
		this.allProjections = allProjections;
	}

	@Override
	public String toString() {
		return "Movies [id=" + id + ", name=" + name + ", year=" + year
				+ ", allProjections=" + allProjections + "]";
	}
	
	
	
}
