package de.hdm.iContacts.client;

import com.google.gwt.core.client.GWT;

import de.hdm.iContacts.shared.CommonSettings;
import de.hdm.iContacts.client.ClientsideSettings;
import de.hdm.iContacts.shared.LoginInfo;
import de.hdm.iContacts.shared.LoginService;
import de.hdm.iContacts.shared.LoginServiceAsync;
import de.hdm.iContacts.shared.IContactsAdministration;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;


public class ClientsideSettings extends CommonSettings{
	
	
	//Leere Objektvariablen, um AdministrationAsync einmalig zu instaziieren

	private static IContactsAdministrationAsync iContactsVerwaltung = null;
	private static LoginServiceAsync loginService = null;
	private static LoginInfo aktuellerUser = null;

	public static LoginServiceAsync getLoginService(){
		if(loginService == null){
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}
	
	/**
	 * gibt den aktuelle eingeloggten User zurueck
	 * @return aktuellerUser
	 */
	public static LoginInfo getAktuellerUser() {
		return aktuellerUser;
	}

	/**
	 * setzt den aktuell eingeloggten User als Userd
	 */
	public static void setAktuellerUser(LoginInfo nutzer) {
		ClientsideSettings.aktuellerUser = nutzer;
	}


	//Sollte es keine Instanz dieser Klasse geben, so wird diese hier erzeugt.
	public static IContactsAdministrationAsync getIContactsVerwaltung(){
		
		if(iContactsVerwaltung == null){
			iContactsVerwaltung = GWT.create(IContactsAdministration.class);
		}
		
		return iContactsVerwaltung;
	}
	


}

