package de.hdm.iContacts.client.navbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.client.form.ContactForm;

public class ShowContactFormNav extends VerticalPanel {
	 
		private VerticalPanel navbar; //von gwt importieren
		private Button showContactlistsButton; // "


		public ShowContactFormNav() {
			navbar = new VerticalPanel(); //wieso kein datentyp
			showContactlistsButton = new Button("Neuer Kontakt");//hier auch
			showContactlistsButton.setStyleName("menubutton");
			showContactlistsButton.addClickHandler(new ShowContactFormClickHandler());

			navbar.add(showContactlistsButton);
			RootPanel.get("Navbar").add(navbar); //importieren
		}
		
		class ShowContactFormClickHandler implements ClickHandler { //importieren von gwt

			public void onClick(ClickEvent event) { //"
				ContactForm contactForm = new ContactForm(); //importieren
			}
	}

}
