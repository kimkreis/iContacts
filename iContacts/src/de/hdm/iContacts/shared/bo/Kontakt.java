package de.hdm.iContacts.shared.bo;

public class Kontakt extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	 // alle variablen ausser id
	private int profilId;
	private String google_id;
	private String vorname;
	private String nachname;
	private String adresse;
	private String eMail;
	private User user;
	
	public int getProfilId() {
		return profilId;
	}

	public void setProfilId(int profilId) {
		this.profilId = profilId;
	}

	public String getGoogleId() {
		return google_id;
	}

	public void setGoogleId(String google_id) {
		this.google_id = google_id;
	}
	
	public void setUser(User user){
		this.user= user;
	}
	public User getUser(){
		return user;
	}
	
	public void setVorname (String vorname) {
		this.vorname = vorname;
	}
	public String getVorname (){
		return vorname;
	}
	public void setNachname (String nachname){
		this.nachname = nachname;
	}
	public String getNachname (){
		return nachname;
	}
	public void setAdresse (String adresse){
		this.adresse = adresse;
	}
	public String getAdresse (){
		return adresse;
	}
	public void setEMail (String eMail){
		this.eMail = eMail;
	}
	public String getEMail (){
		return eMail;
	}
}
