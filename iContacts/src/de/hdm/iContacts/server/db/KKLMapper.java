package de.hdm.iContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.iContacts.shared.bo.Kontakt;
import de.hdm.iContacts.shared.bo.Kontaktliste;

/**
 * Mapper-Klasse, die <code>KKL</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see CustomerMapper, TransactionMapper
 * @author Thies
 */
public class KKLMapper {

  /**
   * Die Klasse KKLMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see kklMapper()
   */
  private static KKLMapper kklMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected KKLMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>KKLMapper.kklMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>KKLMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> KKLMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>KKLMapper</code>-Objekt.
   * @see kklMapper
   */
  public static KKLMapper kklMapper() {
    if (kklMapper == null) {
      kklMapper = new KKLMapper();
    }

    return kklMapper;
  }



  /**
   * Auslesen aller Konten.
   * 
   * @return Ein Vektor mit KKL-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Kontakt> findAllKontakteOf(Kontaktliste kl) {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Kontakt> result = new Vector<Kontakt>();

    try {
      Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT T_KONTAKT.id, T_KONTAKT.vorname, T_KONTAKT.nachname, T_KONTAKT.eMail, T_KONTAKT.adresse INNER JOIN T_KONTAKT ON T_KKL WHERE T_KKL.ID_KONTAKTLISTE =  "
							+ kl.getId() // kkl mapper später fertig stellen
											// (kontakt_id und kl:id)
							+ " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein KKL-Objekt erstellt.
			 while (rs.next()) { //schleife läuft durch, bis es keinen datensatz mehr gibt, gegenteil zu if
			        Kontakt a = new Kontakt();
			        a.setId(rs.getInt("id"));
			        a.setVorname(rs.getString("vorname"));
			        a.setNachname(rs.getString("nachname"));
			        a.setEMail(rs.getString("eMail"));
			        a.setAdresse(rs.getString("adresse"));
			        

			        // Hinzufügen des neuen Objekts zum Ergebnisvektor, da mehrere objekte, nicht nur eins wie bei findbykey
			        result.addElement(a); //vektor wird mit den erzeugten kontakt-okjekten gefüllt
			      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen aller Konten eines durch Fremdschlüssel (Kundennr.) gegebenen
   * Kunden.
   * 
   * @see findByOwner(Customer owner)
   * @param ownerID Schlüssel des zugehörigen Kunden.
   * @return Ein Vektor mit KKL-Objekten, die sämtliche Konten des
   *         betreffenden Kunden repräsentieren. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
 
    // Ergebnisvektor zurückgeben
 

  /**
   * Auslesen aller Konten eines Kunden (durch <code>Customer</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Kundenobjekt, dessen Konten wir auslesen möchten.
   * @return alle Konten des Kunden
   */
 
  /**
   * Einfügen eines <code>KKL</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
 

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param a das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
 
  /**
   * Löschen der Daten eines <code>KKL</code>-Objekts aus der Datenbank.
   * 
   * @param a das aus der DB zu löschende "Objekt"
   */
 

  /**
   * Löschen sämtlicher Konten (<code>KKL</code>-Objekte) eines Kunden.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Customer</code>
   * -Objekt gelöscht wird.
   * 
   * @param c das <code>Customer</code>-Objekt, zu dem die Konten gehören
   */
  public void deleteAllEntriesOf(Kontakt k) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM T_KKL " + "WHERE ID_KONTAKT =" + k.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }
  
  public void deleteAllEntriesOf(Kontaktliste kl) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM T_KKL " + "WHERE ID_KONTAKTLISTE =" + kl.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
  }}

  /**
   * Auslesen des zugehörigen <code>Customer</code>-Objekts zu einem gegebenen
   * Konto.
   * 
   * @param a das Konto, dessen Inhaber wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Kontos darstellt
   */
  

