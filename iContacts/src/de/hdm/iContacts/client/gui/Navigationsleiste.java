package de.hdm.iContacts.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.iContacts.client.ClientsideSettings;
import de.hdm.iContacts.client.gui.Startseite;
import de.hdm.iContacts.shared.IContactsAdministrationAsync;

public class Navigationsleiste extends Composite {

	IContactsAdministrationAsync IContactsVerwaltung = ClientsideSettings.getIContactsVerwaltung();

	private Anchor reportLink = new Anchor("ReportGenerator");
	private Anchor signOutLink = new Anchor();

	private VerticalPanel menuPanel = new VerticalPanel();

	private Button homeButton = new Button("Startseite");
	private Button logout = new Button("Logout");
	private Button reportButton = new Button("Reports");
	private Button fahrtenbuchButton = new Button("Fahrtenbuecher");

	public Navigationsleiste() {

		menuPanel.add(homeButton);
		menuPanel.add(reportButton);
		menuPanel.add(fahrtenbuchButton);
		menuPanel.add(logout);

		// Abstand zwischen den einzelnen Buttons
		menuPanel.setSpacing(20);

		// Layout Button
		homeButton.setPixelSize(200, 40);
		reportButton.setPixelSize(200, 40);
		fahrtenbuchButton.setPixelSize(200, 40);
		logout.setPixelSize(200, 40);

		homeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Label startseiteLabel = new Label("Willkommen!");

				RootPanel.get("contentHeader").clear();
				RootPanel.get("contentHeader").add(startseiteLabel);

				RootPanel.get("content").clear();
				RootPanel.get("content").add(new Startseite());
			}
		});

		
		//TODO: Mit Klick auf diesen Button sollen alle Fahrtenb�cher kommen
				
		fahrtenbuchButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				//FahrtenbuchVerwaltung.
				// TODO Auto-generated method stub
				
			}
					
		});
		
		
		reportButton.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				reportLink.setHref(GWT.getHostPageBaseURL()+"FahrtenbuchReport.html");
				Window.open(reportLink.getHref(), "_self", "");
			}
			
		});
		
		
		logout.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				ClientsideSettings.setAktuellerUser(null);
				signOutLink.setHref(ClientsideSettings.getAktuellerUser().getLogoutUrl());
				Window.Location.assign(signOutLink.getHref());
			}
		});
		
		
		
		initWidget(menuPanel);
	}

	public void homeButtonClick() {
		homeButton.click();

	}

	public void setButtonsEnabled() {
		homeButton.setEnabled(true);
		logout.setEnabled(true);
		reportButton.setEnabled(true);
		fahrtenbuchButton.setEnabled(true);
	}

	public void setButtonsUnabled() {
		homeButton.setEnabled(false);
		logout.setEnabled(false);
		reportButton.setEnabled(false);
		fahrtenbuchButton.setEnabled(false);
	}

}
