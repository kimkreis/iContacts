package de.hdm.iContacts.shared.bo;

public class Kontaktliste extends BusinessObject {
	
	private String name;
	private User user;
	
	public void setUser(User user){
		this.user= user;
	}
	public User getUser(){
		return user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
