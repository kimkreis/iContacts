package de.hdm.iContacts.client.gui;

import com.google.gwt.user.client.ui.Composite;

import de.hdm.iContacts.client.ClientsideSettings;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;

public class Startseite extends Composite{
	
	IContactsAdministrationAsync fahrtenbuchVerwaltung = ClientsideSettings.getIContactsVerwaltung();
}
