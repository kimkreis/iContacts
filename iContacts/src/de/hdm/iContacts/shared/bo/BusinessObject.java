package de.hdm.iContacts.shared.bo;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable { // abstract machen, interface implementieren
	
	private int id;
	public void setId (int id) { // methodenkörper, rumpf; id unten rechts
		this.id = id;  //this.id ist id in zeile 7
	}
	
	public int getId () {
		return id; 
	}
	//getter und setter für jede Variable, da private variablen von außen nicht ansprechbar sind, über methoden sind diese zugreifbar
}
