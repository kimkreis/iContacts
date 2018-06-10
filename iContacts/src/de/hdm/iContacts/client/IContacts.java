package de.hdm.iContacts.client;

import de.hdm.iContacts.client.gui.MessageBox;
import de.hdm.iContacts.client.gui.KontaktClient;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;
import de.hdm.iContacts.client.ClientsideSettings;
import de.hdm.iContacts.shared.LoginServiceAsync;
import de.hdm.iContacts.client.gui.Navigationsleiste;
import de.hdm.iContacts.shared.LoginInfo;
import de.hdm.iContacts.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
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
public class IContacts implements EntryPoint {
		
	IContactsAdministrationAsync IContactsVerwaltung = ClientsideSettings.getIContactsVerwaltung();

	private static String editorHtmlName = "IContacts.html";
	
	static final int REFRESH_INTERVAL = 5000; // ms
	
	public static final Navigationsleiste nt = new Navigationsleiste();


	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Bitte einloggen um auf die Plattform zugreifen zu können");
	private Anchor signInLink = new Anchor("Sign In");
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	
		LoginServiceAsync loginService = ClientsideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName, new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				ClientsideSettings.setAktuellerUser(result);
				if (loginInfo.isLoggedIn()) {
					loadContacts();
					newUserCheck(result);
				} else {
					loadLogin();
				}
			}
		});
	}
	
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginPanel);
	}
	
	private void loadContacts(){
		RootPanel.get("nav").add(nt);
		nt.homeButtonClick();
		
	}
	
	private void newUserCheck(LoginInfo log) {
		IContactsVerwaltung.checkStatus(log, new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				if (!result) {
					nt.setButtonsUnabled();

					MessageBox.alertWidget("Nutzerkonto", "Sie haben noch kein Profil, bitte legen Sie eines an");
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new KontaktClient());
				}
			}

			public void onFailure(Throwable caught) {
			}
		});
	
	}
}
