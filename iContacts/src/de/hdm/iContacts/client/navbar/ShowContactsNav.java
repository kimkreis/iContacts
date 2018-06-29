package de.hdm.iContacts.client.navbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.client.view.ContactsView;

public class ShowContactsNav extends VerticalPanel { //immer so?
 
	private VerticalPanel navbar; //von gwt importieren
	private Button showContactsButton; // "
	
	/**
	 * Der Konstruktor erzeugt ein neues Vertical Panel. Dieser wird der Navbar zugeordnet.
	 *
	 */
	//on modeluload in vorlesungsbsp , wieso?
	
	public ShowContactsNav() { //konstruktor, in mündliche auch?
		navbar = new VerticalPanel();  //objekt, wieso nicht VerticalPanel davor.. panel erzeugen
		showContactsButton = new Button("Kontakte"); //button erzeugen
		showContactsButton.setStyleName("menubutton"); //verweis css, wie nennen in mündliche
		showContactsButton.addClickHandler(new ShowContactsClickHandler()); //clickhandler hinzufügen
		
		/**addStockButton.addClickHandler (new ClickHandler(){ ---- wieso nicht so
		 * public void onClick (Clickevent event) {
		 * add.Stock();
		 * }
		 * });
		*/
		
		navbar.add(showContactsButton); //button dem panel zuordnen
		RootPanel.get("Navbar").add(navbar); //importieren, rootPanel zuweisen, "Navbar": Verwendung der Element-Id aus der HTML-Hostpage
	}//setFocus? newSymbolTextBox.setfocus(true);

	class ShowContactsClickHandler implements ClickHandler { //importieren von gwt, quelle, keine anonyme klasse?

		public void onClick(ClickEvent event) { //senke
			ContactsView contactsView = new ContactsView(); // ?
		}
}
}
