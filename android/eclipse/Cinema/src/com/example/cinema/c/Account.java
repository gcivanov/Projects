package com.example.cinema.c;


public class Account {
	
	private String name;
	private String pass;
	private String email;
	
	public Account() {
		this("","","");
	}
	public Account(String name, String pass , String email){
		setName(name);
		setPass(pass);
		setEmail(email);
	}
	
	public static boolean checkPass(String pass){
		if(pass != null) {
			if(6 <= pass.length() && pass.matches("\\d+.*")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkEmail(String email) {
		if(null != email && email.matches(".+@[a-z]+.[a-z]+") ) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return new String(name);
	}
	public void setName(String name) {
		if(name != null)
			this.name = name;
	}
	public String getPass() {
		return new String(pass);
	}
	public void setPass(String pass) {
		if(checkPass(pass))
			this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(checkEmail(email))
			this.email = email;
	}
}
