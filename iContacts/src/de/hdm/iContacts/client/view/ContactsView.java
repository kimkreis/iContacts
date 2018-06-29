package de.hdm.iContacts.client.view;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.shared.IContactsAdministration;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;
import de.hdm.iContacts.shared.bo.Kontakt;

public class ContactsView extends VerticalPanel{

	 
	    private IContactsAdministrationAsync iContactsAdministration = GWT.create(IContactsAdministration.class);
	   
	
	    private String userEmail;
	    private Label lbl;
	    private Button showOtherProfileButton;
	    private int ContactsCounter = 1;
	    private FlexTable ContactsTable = new FlexTable();
	    private Button backToContactsButton;
	 
	    public ContactsView() {
	 
	        // Content wird geloescht
	        RootPanel.get("Content").clear();
	        RootPanel.get("Subnav").clear();
	        createContactsTable();
	    }
	    public void createContactsTable() {
	        AsyncCallback<Vector<Kontakt>> getAllKontakteCallback = new AsyncCallback<Vector<Kontakt>>() {
	            public void onFailure(Throwable caught) {
	                Window.alert("Kontakte konnten nicht geladen werden");
	            }
	 
	            @Override
	            public void onSuccess(Vector<Kontakt> result) {
	                for (Kontakt kontakt : result) {
	                	
	                    ContactsTable.setText(0, 0, "Vorname");
	                    ContactsTable.setText(0, 1, "Nachname");
	                    ContactsTable.setText(0, 2, "Adresse");
	                    ContactsTable.setText(0, 3, "EMail");
	 
	                    ContactsTable.getCellFormatter().addStyleName(0, 0, "flexTableColumns");
	                    ContactsTable.getCellFormatter().addStyleName(0, 1, "flexTableColumns");
	                    ContactsTable.getCellFormatter().addStyleName(0, 2, "flexTableColumns");
	                    ContactsTable.getCellFormatter().addStyleName(0, 3, "flexTableColumns");
	 
	                    ContactsTable.addStyleName("flexTable");
	                    ContactsTable.getRowFormatter().addStyleName(0, "flexTableHeader");
	 
	                    ContactsTable.setText(ContactsCounter, 0, kontakt.getVorname());
	                    ContactsTable.setText(ContactsCounter, 1, kontakt.getNachname());
	                    ContactsTable.setText(ContactsCounter, 2, kontakt.getAdresse());
	                    ContactsTable.setText(ContactsCounter, 3, kontakt.getEMail());
	 
	                    if (ContactsCounter % 2 == 0) {
	                        ContactsTable.getRowFormatter().addStyleName(ContactsCounter, "flexTableRow0");
	                    } else {
	                        ContactsTable.getRowFormatter().addStyleName(ContactsCounter, "flexTableRow1");
	                    }
	 
	                    ContactsCounter++;
	                }
	 
	            }
	        };
	        iContactsAdministration.getAllKontakte(getAllKontakteCallback); //aufruf zu methode der impl klasse
	 
	        RootPanel.get("Content").add(ContactsTable); //fehlerbehebung //alles im war sucht server ressourcen  im lib, alles im war wird lokal deployed
	       //this.add(ContactsTable);
	    }
}