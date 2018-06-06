package de.hdm.iContacts.shared.bo;

import java.util.Vector;

public class User extends BusinessObject { // besitzt bo methoden

	private String vorname;
	private String nachname;
	private String eMail;
	private Vector<Kontaktliste> kontaktlisten = new Vector<Kontaktliste>();
	private Vector<Kontakt> kontakte = new Vector<Kontakt>();
	
	public void add(Kontaktliste k){ //element zu vector hinzufügen
		kontaktlisten.addElement(k);
	}
	public Vector<Kontaktliste> getKontaktlisten() {
		return kontaktlisten;
	}
	
	public void add(Kontakt k){
		kontakte.addElement(k);
	}
	public Vector<Kontakt> getKontakte(){
		return kontakte;
	}
	
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
