package de.hdm.iContacts.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.iContacts.server.db.KontaktMapper;
import de.hdm.iContacts.server.db.KontaktlisteMapper;
import de.hdm.iContacts.server.db.UserMapper;
import de.hdm.iContacts.shared.IContactsAdministration;
import de.hdm.iContacts.shared.bo.Kontakt;
import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.shared.bo.User;

public class IContactsAdministrationImpl extends RemoteServiceServlet implements IContactsAdministration {

	private static final long serialVersionUID = 6437184998312943498L;
	private KontaktMapper kMapper = null; // auch gleich null wie bank?
	private KontaktlisteMapper klMapper = null;
	private UserMapper userMapper = null;

	@Override
	public void init() throws IllegalArgumentException {
		// TODO Auto-generated method stub
	    this.kMapper = KontaktMapper.kontaktMapper();
	    this.klMapper = KontaktlisteMapper.kontaktlisteMapper();
	    this.userMapper = UserMapper.userMapper();
	
	}
	 
	//Ganz wesentlich ist, dass die BankAdministration einen vollständigen Satz von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank kommunizieren kann
	
	@Override
	public User save(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (userMapper.findBy(user) == null) {
			userMapper.insert(user); // usermapper such eintrag von user
		} else {
			userMapper.update(user);
		}

		return user;

	}

	@Override
	public Kontaktliste save(Kontaktliste kl) throws IllegalArgumentException { //umbenannt in kontaktliste kl weil kontakt k
		// TODO Auto-generated method stub
		if (klMapper.findByKey(kl.getId()) == null){
			klMapper.insert(kl);
			
		} else {
			klMapper.update(kl);
		}
		return kl;
	}

	@Override
	public Kontakt save(Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (kMapper.findByKey(k.getId()) == null){
			kMapper.insert(k);
			
		} else {
			kMapper.update(k);
		}
		return k;
	}

	@Override
	public void delete(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		 /*
	     * Zunächst werden sämtl. Konten des Kunden aus der DB entfernt.
	     * 
	     * Beachten Sie, dass wir dies auf Ebene der Applikationslogik, konkret: in
	     * der Klasse BankVerwaltungImpl, durchführen. Grund: In der Klasse
	     * BankVerwaltungImpl ist die Verflechtung sämtlicher Klassen bzw. ihrer
	     * Objekte bekannt. Nur hier kann sinnvoll ein umfassender Verwaltungsakt
	     * wie z.B. dieser Löschvorgang realisiert werden.
	     * 
	     * Natürlich könnte man argumentieren, dass dies auch auf Datenbankebene
	     * (sprich: mit SQL) effizienter möglich ist. Das Gegenargument ist jedoch
	     * eine dramatische Verschlechterung der Wartbarkeit Ihres Gesamtsystems
	     * durch einen zu niedrigen Abstraktionsgrad und der Verortung von Aufgaben
	     * an einer Stelle (Datenbankschicht), die die zuvor genannte Verflechtung
	     * nicht umfänglich kennen kann.
	     */
		Vector<Kontakt> kontakte = kMapper.getAllKontakteOf(user);
		if (kontakte != null) {
			for (Kontakt k : kontakte) {
				// erst alle konatkte und listen löschen bevor user gelöscht
				// werden kann
				this.delete(k);
			}
		}

		// Anschließend den User entfernen.. erst unten

		Vector<Kontaktliste> kontaktlisten = klMapper.findByUser(user);

		if (kontaktlisten != null) {
			for (Kontaktliste kl : kontaktlisten) {
				// erst alle konatkte und listen läöschen bevor user gelöschz
				// werden kann
				this.delete(kl);
			}
		}

		// Anschließend den User entfernen, erst hier, nicht schon nach kontakten
		this.userMapper.delete(user);
	}
	

	@Override
	public void delete(Kontakt k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		 
		//kklMapper.deleteAllEntriesOf(k);
		this.kMapper.delete(k);
		 
	}

	@Override
	public void delete(Kontaktliste k) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		//kklMapper.deleteAllEntriesOf(kl);
		this.klMapper.delete(k);
	
	}

	@Override //überschriebene, nicht überladene Methode, vererbung, annotation
	public Vector<Kontakt> getAllKontakte() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Vector<Kontakt> result = this.kMapper.findAll();
		return result;
		//return null;
	}

	@Override
	public Vector<Kontaktliste> getAllKontaktlisten() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.klMapper.findAll(); //rpc return, schickt den kommenden vektor zurück ins client, aufruf und antwort
		//return null;
	}

	@Override
	public Vector<Kontakt> getAllKontakeOf(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.kMapper.getAllKontakteOf(user); //findAllKontakteOfUser 
		//return null;
	}

	@Override
	public Vector<Kontaktliste> getAllKontaktlistenOf(User user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return this.klMapper.findByUser(user);
		//return null;
	}

	@Override
	public Vector<Kontakt> getAllKontakteOf(Kontaktliste kl) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	//	Vector <Kontakt> k = kklMapper.findAllKontakteOf(kl);
		return null;
		//return null;
	}
	

}
