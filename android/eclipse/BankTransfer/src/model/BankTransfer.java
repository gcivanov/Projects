package model;

import java.io.Serializable;

public abstract class BankTransfer implements Authorization, Serializable{
	
	private static final long serialVersionUID = 731225597072248736L;
	
	private String fromIBAN;
	private String toIBAN;
	
	private String text;
	private double money;

	
	public void initTransfer(){
		//TODO
	}
	public void execTransfer(){
		//TODO
	}
	
	public String getFromIBAN() {
		return fromIBAN;
	}
	public void setFromIBAN(String fromIBAN) {
		this.fromIBAN = fromIBAN;
	}
	public String getToIBAN() {
		return toIBAN;
	}
	public void setToIBAN(String toIBAN) {
		this.toIBAN = toIBAN;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	

	@Override
	public String toString() {
		return "BankTransfer from IBAN: " + fromIBAN + ", to IBAN:" + toIBAN
				+ ", text: " + text + ", money= " + money + "";
	}
	@Override
	public abstract boolean makeAuthorization(int code);
	
}
