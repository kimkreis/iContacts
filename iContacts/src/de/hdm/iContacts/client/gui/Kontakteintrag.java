package de.hdm.iContacts.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.iContacts.client.ClientsideSettings;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;

public class Kontakteintrag {
	

	IContactsAdministrationAsync IContactsVerwaltung = ClientsideSettings.getIContactsVerwaltung();
	
	
	FlexTable flex = new FlexTable();
	FlexTable eigFlex = new FlexTable();
	FlexTable buttonFlex = new FlexTable();
	DialogBox db = new DialogBox();
	VerticalPanel vPan = new VerticalPanel();
	
	HTML heading = new HTML("<p class='heading'>Neue Ausschreibung:</p>");
	HTML heading2 = new HTML("<p class='heading'>Eigenschaften:</p>");
	
	
	Button savenew = new Button("Speichern");
	Button update = new Button("Speichernnnn");
	Button cancel = new Button("Abbrechen");
	//TODO: bewerben
	//TODO: Standardeigenschaften
	Button change = new Button("Bearbeiten");
	Button quit = new Button("Ausschreibung abbrechen");
	Button delete = new Button("Ausschreibung löschen");
	Button addEig	= new Button("Eigenschaft hinzufügen");
	Button bewerben = new Button("Auf Ausschreibung bewerben");
	
 
	Label bezeichng = new Label("Stellenbezeichnung: ");
	Label projektleitr = new Label("Projektleiter: ");
	Label bewerbungsfrst = new Label("Bewerbungsfrist: ");
	HTML ausschrtext = new HTML("Ausschreibungstext: ");
	
	//TODO: Buttongröße
	//TODO: Ausschreibung löschen
	//TODO: Ausschreibung abbrechen
	TextBox bezeichnung = new TextBox();
	TextBox projektleiter = new TextBox();
	DateBox bewerbungsfrist = new DateBox();
	TextArea ausschreibungstext = new TextArea();
	
	
  }
	
