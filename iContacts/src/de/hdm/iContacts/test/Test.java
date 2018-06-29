package de.hdm.iContacts.test;

import java.util.Vector;

import de.hdm.iContacts.server.IContactsAdministrationImpl;
import de.hdm.iContacts.server.db.UserMapper;
import de.hdm.iContacts.shared.bo.Kontakt;
import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.shared.bo.User;

public class Test {

	public static void main (String [] args){ //static =von überall zugreifbar, args = argumente
		IContactsAdministrationImpl impl= new IContactsAdministrationImpl();
		impl.init();
//		User user = new User();
//		user.setEMail("random.hdm@stuttgart.de");
//		user.setNachname("Bauer");
//		user.setVorname("Peter"); //in db reinschreiben, id automatisch
//		//contact obj erstellen, delete, save etc, impl.delete()
//		
//		impl.save(user); //insert in den mappern - ist sql spezifisch, in applogik save
		
		Vector<Kontaktliste> kontakte = impl.getAllKontaktlisten();
		for(Kontaktliste kontakt : kontakte) {
			System.out.println(kontakt.getName());
		}
			
}
}
