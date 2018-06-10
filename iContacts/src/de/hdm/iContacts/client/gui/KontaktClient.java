package de.hdm.iContacts.client.gui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.client.ClientsideSettings;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;
import de.hdm.iContacts.shared.bo.Kontakt;

public class KontaktClient extends Composite {

	IContactsAdministrationAsync IContactsVerwaltung = ClientsideSettings.getIContactsVerwaltung();

	FlexTable flexTable = new FlexTable();
	FlexTable additionalEigenschaften = new FlexTable();
	Label email = new Label("E-Mail:");
	TextBox mail = new TextBox();
	Label nutzerVorName = new Label("Vorname:");
	TextBox nutzerVorNam = new TextBox();
	Label nutzerNachName = new Label("Nachname:");
	TextBox nutzerNachNam = new TextBox();
	
	Button add = new Button("Eigenschaft hinzufügen");
	Button speichern = new Button("Speichern");
	Button deleteOrga = new Button("Nutzerprofil löschen");
	Button bearbeiten = new Button("Nutzername bearbeiten");
	// Button save = new Button("Nutzername speichern");

	Kontakt neuerKontakt= new Kontakt();

	VerticalPanel vPanel = new VerticalPanel();

	public KontaktClient() {

		RootPanel.get("contentHeader").clear();
		Label newProfil = new Label("neuer Nutzer anlegen");
		RootPanel.get("contentHeader").add(newProfil);

		vPanel.add(add);

		mail.setValue(ClientsideSettings.getAktuellerUser().getEmailAddress());
		mail.setEnabled(false);

		flexTable.setWidget(0, 0, email);
		flexTable.setWidget(0, 1, mail);
		flexTable.setWidget(1, 0, nutzerVorName);
		flexTable.setWidget(1, 1, nutzerVorNam);
		flexTable.setWidget(2, 0, nutzerNachName);
		flexTable.setWidget(2, 1, nutzerNachNam);

		
		
		speichern.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (mail.getValue().isEmpty() || nutzerVorNam.getValue().isEmpty() || nutzerNachNam.getValue().isEmpty()) {
					Window.alert("Bitte Feld ausfuellen!");
				} else {
					
					IContactsVerwaltung.createKontakt(new AsyncCallback<Kontakt>() {
						
						@Override
						public void onSuccess(Kontakt result){
							neuerKontakt = result;
							
							speichern.setEnabled(false);
							neuerKontakt.setGoogleId(mail.getValue());
							neuerKontakt.setVorname(nutzerVorNam.getValue());
							neuerKontakt.setNachname(nutzerNachNam.getValue());
							neuerKontakt.setProfilId(neuerKontakt.getProfilId());
							
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
							
						}
					});

				}
			}

		});
	}

}

