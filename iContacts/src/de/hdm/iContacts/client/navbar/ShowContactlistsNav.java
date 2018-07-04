package de.hdm.iContacts.client.navbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.client.view.ContactsListView;

public class ShowContactlistsNav extends VerticalPanel {
 
	private VerticalPanel navbar; //von gwt importieren
	private Button showContactlistsButton; // "


	public ShowContactlistsNav() {
		navbar = new VerticalPanel(); //wieso kein datentyp
		showContactlistsButton = new Button("Kontaktlisten");//hier auch
		showContactlistsButton.setStyleName("menubutton");
		showContactlistsButton.addClickHandler(new ShowContactlistsClickHandler());

		navbar.add(showContactlistsButton);
		RootPanel.get("Navbar").add(navbar); //importieren
	}
	
	class ShowContactlistsClickHandler implements ClickHandler { //importieren von gwt

		public void onClick(ClickEvent event) { //"
			ContactsListView ContactlistsView = new ContactsListView(); //importieren
		}
}
}
