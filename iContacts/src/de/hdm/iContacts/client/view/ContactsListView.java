package de.hdm.iContacts.client.view;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.iContacts.shared.IContactsAdministration;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;
import de.hdm.iContacts.shared.bo.Kontaktliste;

public class ContactsListView {

	 
	    private IContactsAdministrationAsync iContactsAdministration = GWT.create(IContactsAdministration.class);
	 
	    private int ContactsListCounter = 1;
	    private FlexTable ContactsListTable = new FlexTable();
	 
	    public ContactsListView() {
	 
	        // Content wird geloescht
	        RootPanel.get("Content").clear();
	        RootPanel.get("Subnav").clear();
	        createContactsListTable();
	    }
	    public void createContactsListTable() {
	        AsyncCallback<Vector<Kontaktliste>> getAllKontaktlistenCallback = new AsyncCallback<Vector<Kontaktliste>>() {
	            public void onFailure(Throwable caught) {
	                Window.alert("kontaktlistee konnten nicht geladen werden");
	            }
	 
	            @Override
	            public void onSuccess(Vector<Kontaktliste> result) {
	                for (Kontaktliste kontaktliste : result) {
	 
	                    ContactsListTable.setText(0, 0, "Name");
	                    ContactsListTable.setText(0, 1, "Kontakte");
	
	 
	                    ContactsListTable.getCellFormatter().addStyleName(0, 0, "flexTableColumns");
	                    ContactsListTable.getCellFormatter().addStyleName(0, 1, "flexTableColumns");
	             
	 
	                    ContactsListTable.addStyleName("flexTable");
	                    ContactsListTable.getRowFormatter().addStyleName(0, "flexTableHeader");
	 
	                    ContactsListTable.setText(ContactsListCounter, 0, kontaktliste.getName());
	                    //ContactsListTable.setWidget(ContactsListCounter, 1);
	                  
	 
	                    if (ContactsListCounter % 2 == 0) {
	                        ContactsListTable.getRowFormatter().addStyleName(ContactsListCounter, "flexTableRow0");
	                    } else {
	                        ContactsListTable.getRowFormatter().addStyleName(ContactsListCounter, "flexTableRow1");
	                    }
	 
	                    ContactsListCounter++;
	                }
	 
	            }
	        };
	        iContactsAdministration.getAllKontaktlisten(getAllKontaktlistenCallback);
	 
	        RootPanel.get("Content").add(ContactsListTable);
	    }
}