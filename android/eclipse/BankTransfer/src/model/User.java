package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

	private static final long serialVersionUID = -5082613608804601170L;
	
	private String username;
	private String pass;
	private List<String> myIBANs;
	private List<String> uIBANs;
	
	public User(){
		myIBANs = new ArrayList<String>();
		uIBANs = new ArrayList<String>();
	}
	
	public User(String name) {
		this.username = name;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public List<String> getMyIBANs() {
		return myIBANs;
	}
	public void setMyIBANs(List<String> myIBANs) {
		this.myIBANs = myIBANs;
	}
	public List<String> getuIBANs() {
		return uIBANs;
	}
	public void setuIBANs(List<String> uIBANs) {
		this.uIBANs = uIBANs;
	}
	
	public void addUIBAN(String iban) {
		if(iban != null) {
			this.uIBANs.add(iban);
		}
	}
	

}
