package de.hdm.iContacts.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.iContacts.shared.bo.Kontakt;
import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.shared.bo.User;

/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Banken.
 * </p>
 * <p>
 * <b>Frage:</b> Warum werden diese Methoden nicht als Teil der Klassen
 * {@link Bank}, {@link Customer}, {@link Account} oder {@link Transaction}
 * implementiert?<br>
 * <b>Antwort:</b> Z.B. das Löschen eines Kunden erfordert Kenntnisse über die
 * Verflechtung eines Kunden mit Konto-Objekten. Um die Klasse <code>Bank</code>
 * bzw. <code>Customer</code> nicht zu stark an andere Klassen zu koppeln, wird
 * das Wissen darüber, wie einzelne "Daten"-Objekte koexistieren, in der
 * vorliegenden Klasse gekapselt.
 * </p>
 * <p>
 * Natürlich existieren Frameworks wie etwa Hibernate, die dies auf eine andere
 * Weise realisieren. Wir haben jedoch ganz bewusst auf deren Nutzung
 * verzichtet, um in diesem kleinen Demoprojekt den Blick auf das Wesentliche
 * nicht unnötig zu verstellen.
 * </p>
 * <p>
 * <code>@RemoteServiceRelativePath("bankadministration")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an.
 * </p>
 * 
 * @author Thies
 */
@RemoteServiceRelativePath("icontacts")
public interface IContactsAdministration extends RemoteService {

  /**
   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
   * {@link BankVerwaltungImpl} notwendig. Bitte diese Methode direkt nach der
   * Instantiierung aufrufen.
   * 
   * @throws IllegalArgumentException
   */
  public void init() throws IllegalArgumentException; //brauch ich nicht

  /**
   * Einen Kunden anlegen.
   * 
   * @param first Vorname
   * @param last Nachname
   * @return Ein fertiges Kunden-Objekt.
   * @throws IllegalArgumentException
   */
  public User save (User user)
      throws IllegalArgumentException;
  
  public Kontaktliste save (Kontaktliste k)
	      throws IllegalArgumentException;
  
  public Kontakt save (Kontakt k)
	      throws IllegalArgumentException;
  
  public void delete (User user)
		  throws IllegalArgumentException;
  
  public void delete (Kontakt k)
		  throws IllegalArgumentException;
  
  public void delete (Kontaktliste k)
		  throws IllegalArgumentException;
  
// wenn user groß fehler, wieso? --> ändern in admin async
		 
  public Vector<Kontakt> getAllKontakte ()
		  throws IllegalArgumentException;
  
  public Vector<Kontaktliste> getAllKontaktlisten ()
		  throws IllegalArgumentException;
  
  public Vector<Kontakt> getAllKontakeOf (User user) // wir schicken ein user objekt an die impl, impl schickt es zurück aber asynchron, vector return object
		  throws IllegalArgumentException; //fehlerbehandlung
  
  public Vector<Kontaktliste> getAllKontaktlistenOf (User user)
		  throws IllegalArgumentException;
  
  public Vector<Kontakt> getAllKontakteOf (Kontaktliste k)  
		  throws IllegalArgumentException;
  
}