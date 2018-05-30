package de.hdm.iContacts.shared.bo;

public class User extends BusinessObject { // besitzt bo methoden

	private String vorname;
	private String nachname;
	private String eMail;
	
	public void setVorname (String vorname){ //neuen wert setzen
		this.vorname = vorname;
	}
	public String getVorname (){ //was zurückbekommen, in der Regel kein Parameter
		return vorname;
	}
	
	public void setNachname (String nachname){
		this.nachname = nachname;
	}
	public String getNachname (){ 
		return nachname;
	}
	public void setEMail (String eMail){
		this.eMail = eMail;
	}
	public String getEMail (){
		return eMail;
	}
}
