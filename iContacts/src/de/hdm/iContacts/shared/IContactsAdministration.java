package de.hdm.iContacts.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.client.gui.Kontakteintrag;
import de.hdm.iContacts.shared.bo.Kontakt;

public interface IContactsAdministration extends RemoteService{
	
	public void init() throws IllegalArgumentException;

	boolean checkStatus(LoginInfo log);

	public Kontakt createKontakt() throws IllegalArgumentException;
	
	public Kontakteintrag createKontakteintrag(Kontakteintrag kontakteintrag, int nutzerId) throws IllegalArgumentException;
	
	public void deleteKontakteintrag(Kontakteintrag kontakteintrag) throws IllegalArgumentException;
	
	public void updateKontakteintrag(Kontakteintrag kontakteintrag) throws IllegalArgumentException;

}