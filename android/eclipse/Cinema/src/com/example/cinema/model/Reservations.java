package com.example.cinema.model;

import java.io.Serializable;
import java.util.Date;

	

	public class Reservations implements Serializable {
	
		
		private static final long serialVersionUID = -2663487377307587268L;
		
		private long id;
		
		
		private Users user;  /////
		
		private AllProjections allProjections; ////
		
		private static Date dateReservation;
		
		private String busySeats;

	
	
	public Reservations(){
	}
	
	
	
	public long getId() {
		return id;
	}
	
	

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public AllProjections getAllProjections() {
		return allProjections;
	}

	public void setAllProjections(AllProjections allProjections) {
		this.allProjections = allProjections;
	}

	

	public static Date getDateReservation() {
		return dateReservation;
	}



	public static void setDateReservation(Date dateReservation) {
		Reservations.dateReservation = dateReservation;
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
				+ ", allProjections=" + allProjections + ", busySeats="
				+ busySeats + "]";
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
