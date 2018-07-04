package de.hdm.iContacts.client.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.shared.IContactsAdministration;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;
import de.hdm.iContacts.shared.bo.Kontakt;


public class ContactForm extends VerticalPanel {
	

		private IContactsAdministrationAsync iContactsAdministration = GWT.create(IContactsAdministration.class);
		
		private Label instructionsLabel;
		private FlexTable contactAttributeTable;
		private Button saveChangesButton;
		private String email;
		private String firstName;
		private TextBox firstNameBox;
		private String lastName;
		private TextBox lastNameBox;
		private String adresse;
		private TextBox adresseBox;

		private TextBox emailLbl;
		private Button backToIContactsButton;
		private Kontakt newKontakt;
		

		
		public ContactForm() {

			RootPanel.get("Content").clear();
			RootPanel.get("Subnav").clear();


			instructionsLabel = new Label("Bitte geben Sie die Kontaktinformationen an."
					+ "Füllen Sie bitte die Textfelder aus.");
			RootPanel.get("Content").add(instructionsLabel);
			Label umbruch = new Label("");
			RootPanel.get("Content").add(umbruch);
			Label hinweis = new Label("<<<< Wichtig: Alle *Felder müssen ausgefüllt werden. >>>>");
			RootPanel.get("Content").add(hinweis);
			


			/** Tabelle mit Profilaatributen wird erzeugt & anschließend befüllt */
			contactAttributeTable = new FlexTable();
			contactAttributeTable.setText(0, 0, "E-Mail*");
			contactAttributeTable.setText(1, 0, "Vorname*");
			contactAttributeTable.setText(2, 0, "Nachname*");
			contactAttributeTable.setText(3, 0, "Adresse*");

			emailLbl = new TextBox();
			contactAttributeTable.setWidget(0, 1, emailLbl);
			firstNameBox = new TextBox();
			contactAttributeTable.setWidget(1, 1, firstNameBox);
			lastNameBox = new TextBox();
			contactAttributeTable.setWidget(2, 1, lastNameBox);
			adresseBox = new TextBox();
			contactAttributeTable.setWidget(3, 1, adresseBox);
		
			RootPanel.get("Content").add(contactAttributeTable);

			saveChangesButton = new Button("Speichern");
			saveChangesButton.setStyleName("menubutton");
			saveChangesButton.addClickHandler(new SaveNewContactClickHandler());
			RootPanel.get("Content").add(saveChangesButton);

			backToIContactsButton = new Button("Abbrechen");
			backToIContactsButton.setStyleName("menubutton");
			
			backToIContactsButton.addClickHandler(new AbortClickHandler());
			RootPanel.get("Content").add(backToIContactsButton);
		}

	
		class AbortClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				Location.assign("iContacts.html");	}
		}

	
		class SaveNewContactClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
			
				newKontakt = new Kontakt();

				emailLbl = (TextBox) contactAttributeTable.getWidget(0, 1);
				email = emailLbl.getText();
				newKontakt.setEMail(email); //obj hinzufügen

				firstNameBox = (TextBox) contactAttributeTable.getWidget(1, 1);
				firstName = firstNameBox.getValue();
				if (firstNameBox.getValue() == "") {
					Window.alert("Bitte geben Sie Ihren Vornamen ein");
					return;
				}
				newKontakt.setVorname(firstName);

				
				lastNameBox = (TextBox) contactAttributeTable.getWidget(2, 1);
				lastName = lastNameBox.getValue();
				if (lastNameBox.getValue() == "") {
					Window.alert("Bitte geben Sie Ihren Nachnamen ein");
					return;
				}
				newKontakt.setNachname(lastName);

				adresseBox = (TextBox) contactAttributeTable.getWidget(3, 1);
				adresse = adresseBox.getValue();
				if (adresseBox.getValue() == "") {
					Window.alert("Bitte geben Sie Ihre Adresse ein");
					return;
				}
				newKontakt.setAdresse(adresse);

				AsyncCallback<Kontakt> saveKontaktCallback = new AsyncCallback<Kontakt>() { //anonyme klasse oder class callback
					public void onFailure(Throwable caught) {
						Window.alert("Kontakt konnte nicht gespeichert werden.");
					}

					public void onSuccess(Kontakt result) {
						Window.alert(
								"Ihr Kontakt wurde erfolgreich für Sie angelegt.");
						Location.assign("iContacts.html");
					}
				};
				//Proxy: iContactsAdministration
				iContactsAdministration.save(newKontakt, saveKontaktCallback);
			}
		}
	}
