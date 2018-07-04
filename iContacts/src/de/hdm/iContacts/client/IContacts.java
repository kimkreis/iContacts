package de.hdm.iContacts.client;

import de.hdm.iContacts.client.form.ContactForm;
import de.hdm.iContacts.client.navbar.ShowContactFormNav;
import de.hdm.iContacts.client.navbar.ShowContactlistsNav;
import de.hdm.iContacts.client.navbar.ShowContactsNav;
import de.hdm.iContacts.shared.FieldVerifier;
import de.hdm.iContacts.shared.IContactsAdministration;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IContacts implements EntryPoint { //entrypoint festlegen, Einstiegsklasse der GWT-Anwendung
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
		
	
	private VerticalPanel navbar;
	private VerticalPanel content;
	private Label lbl;
	private Label lbl2;
	private Button logoutButton;
	private ShowContactsNav showContactsNav;
	private ShowContactlistsNav showContactlistsNav;
	private ShowContactFormNav showContactFormNav;

	//private final IContactsAdministrationAsync iContactsAdministration = GWT.create(IContactsAdministration.class); //proxy-obj. erzeugen
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() { //Startpunkt der GWT-Anwendung, ähnlich wie main, nur für Startklassen
		
		navbar = new VerticalPanel();
		showContactsNav = new ShowContactsNav();
		showContactlistsNav = new ShowContactlistsNav();
		showContactFormNav = new ShowContactFormNav();
		 
		logoutButton = new Button("Logout"); //button erstellen
		
		//logoutButton.addClickHandler(new ClickHandler() { //senke
			//public void onClick (ClickEvent event){ //quelle
				//logout(); //logout code
			//}
		//});
		        logoutButton.setStyleName("gwt-Red-Button"); // in css definiert
		        logoutButton.addClickHandler(new LogoutClickHandler()); //clickhandler hinzufügen, ausloggen würde mit google login gehen
		RootPanel.get("Navbar").add(logoutButton); //links
		content = new VerticalPanel();
		        lbl = new Label("Willkommen bei iContacts");
		        lbl.setStyleName("headline");
		        lbl2 = new Label("Hier können Sie ihe Kontakte verwalten");
		        //Image img2 = new Image("../images/love.png");
		        //img2.setStyleName("picture");
		        content.add(lbl);
		        content.add(lbl2);
		        //content.add(img2);
		 
		RootPanel.get("Content").add(content);

	}
	class LogoutClickHandler implements ClickHandler { //klasse, die clickhandler implementiert; anonyme nur wenn es einmal vorkommt, fast nie
		public void onClick(ClickEvent event) {
			//String signIn = loginInfo.getLogoutUrl().toString();
			//Window.Location.assign(signIn);
		}
}
}
