package de.hdm.iContacts.server;

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
  public static KKLMapper userMapper() {
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
  public Vector<Kontakt> findAllKontakteOf(Kontaktliste k) {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Kontakt> result = new Vector<Kontakt>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, eMail, adresse FROM kkls " //kkl mapper später fertig stellen (kontakt_id und kl:id)
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein KKL-Objekt erstellt.
      while (rs.next()) {
        KKL a = new KKL();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(a);
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
  public Vector<KKL> findByOwner(int ownerID) {
    Connection con = DBConnection.connection();
    Vector<KKL> result = new Vector<KKL>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM kkls "
          + "WHERE owner=" + ownerID + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein KKL-Objekt erstellt.
      while (rs.next()) {
        KKL a = new KKL();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen aller Konten eines Kunden (durch <code>Customer</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Kundenobjekt, dessen Konten wir auslesen möchten.
   * @return alle Konten des Kunden
   */
  public Vector<KKL> findByOwner(Customer owner) {

    /*
     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByOwner(owner.getId());
  }

  /**
   * Einfügen eines <code>KKL</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public KKL insert(KKL a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM kkls ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * a erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        a.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO kkls (id, owner) " + "VALUES ("
            + a.getId() + "," + a.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten KKLs.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des KKL-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von a ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return a;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param a das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public KKL update(KKL a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE kkls " + "SET owner=\"" + a.getOwnerID()
          + "\" " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(KKL a) zu wahren, geben wir a zurück
    return a;
  }

  /**
   * Löschen der Daten eines <code>KKL</code>-Objekts aus der Datenbank.
   * 
   * @param a das aus der DB zu löschende "Objekt"
   */
  public void delete(KKL a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM kkls " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Konten (<code>KKL</code>-Objekte) eines Kunden.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Customer</code>
   * -Objekt gelöscht wird.
   * 
   * @param c das <code>Customer</code>-Objekt, zu dem die Konten gehören
   */
  public void deleteKKLsOf(Customer c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM kkls " + "WHERE owner=" + c.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen des zugehörigen <code>Customer</code>-Objekts zu einem gegebenen
   * Konto.
   * 
   * @param a das Konto, dessen Inhaber wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Kontos darstellt
   */
  public Customer getOwner(KKL a) {
    /*
     * Wir bedienen uns hier einfach des CustomerMapper. Diesem geben wir
     * einfach den in dem s-Objekt enthaltenen Fremdschlüssel für den
     * Kontoinhaber. Der CustomerMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return CustomerMapper.customerMapper().findByKey(a.getOwnerID());
  }

}


