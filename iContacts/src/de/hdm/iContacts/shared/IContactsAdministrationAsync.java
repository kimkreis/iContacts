package de.hdm.iContacts.shared;

import java.util.Vector; // interface wird im hintergrund implementiert - IContactsAdministration, wie von IContactsAdministrationImpl

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.iContacts.shared.bo.Kontakt;
import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.shared.bo.User;

public interface IContactsAdministrationAsync { 

	void delete(User user, AsyncCallback<Void> callback); //löschen immer void

	void delete(Kontakt k, AsyncCallback<Void> callback); //async call back passiert im hintegrund, art nummer, proxy ist platzhalter

	void delete(Kontaktliste k, AsyncCallback<Void> callback);

	void getAllKontakeOf(User user, AsyncCallback<Vector<Kontakt>> callback);

	void getAllKontakteOf(Kontaktliste k, AsyncCallback<Vector<Kontakt>> callback);

	void getAllKontaktlistenOf(User user, AsyncCallback<Vector<Kontaktliste>> callback);

	void getAllKontakte(AsyncCallback<Vector<Kontakt>> callback);

	void getAllKontaktlisten(AsyncCallback<Vector<Kontaktliste>> callback);

	void init(AsyncCallback<Void> callback);

	void save(User user, AsyncCallback<User> callback);

	void save(Kontaktliste k, AsyncCallback<Kontaktliste> callback);

	void save(Kontakt k, AsyncCallback<Kontakt> callback);

}
