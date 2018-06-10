package de.hdm.iContacts.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.client.gui.Kontakteintrag;
import de.hdm.iContacts.shared.bo.Kontakt;

public interface IContactsAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void checkStatus(LoginInfo log, AsyncCallback<Boolean> asyncCallback);

	void createKontakt(AsyncCallback<Kontakt> asyncCallback);

	void createKontakteintrag(Kontakteintrag kontakteintrag, int nutzerId,
			AsyncCallback<Kontakteintrag> callback);

	void deleteKontakteintrag(Kontakteintrag kontakteintrag, AsyncCallback<Void> callback);

	void updateKontakteintrag(Kontakteintrag kontakteintrag, AsyncCallback<Void> callback);
}
