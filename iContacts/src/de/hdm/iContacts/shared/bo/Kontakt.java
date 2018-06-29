package de.hdm.iContacts.shared.bo;

public class Kontakt extends BusinessObject {
	
	 // alle variablen ausser id
	private String vorname;
	private String nachname;
	private String adresse;
	private String eMail;
	
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
