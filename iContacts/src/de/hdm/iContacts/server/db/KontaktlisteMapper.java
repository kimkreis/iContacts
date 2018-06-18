package de.hdm.iContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.iContacts.shared.bo.Kontaktliste;
import de.hdm.iContacts.shared.bo.User;

/**
 * Mapper-Klasse, die <code>Kontaktliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see CustomerMapper, TransactionMapper
 * @author Thies
 */
public class KontaktlisteMapper {

  /**
   * Die Klasse KontaktlisteMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see kontaktlisteMapper()
   */
  private static KontaktlisteMapper kontaktlisteMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected KontaktlisteMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>KontaktlisteMapper.kontaktlisteMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>KontaktlisteMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> KontaktlisteMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>KontaktlisteMapper</code>-Objekt.
   * @see kontaktlisteMapper
   */
  public static KontaktlisteMapper kontaktlisteMapper() {
    if (kontaktlisteMapper == null) {
      kontaktlisteMapper = new KontaktlisteMapper();
    }

    return kontaktlisteMapper;
  }

  /**
   * Suchen eines Kontos mit vorgegebener Kontonummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Konto-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Kontaktliste findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM T_KONTAKTLISTE "
          + "WHERE id=" + id + " ORDER BY owner");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Kontaktliste a = new Kontaktliste();
        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));
        return a;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Konten.
   * 
   * @return Ein Vektor mit Kontaktliste-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Kontaktliste> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Kontaktliste> result = new Vector<Kontaktliste>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM T_KONTAKTLISTE "
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Kontaktliste-Objekt erstellt.
      while (rs.next()) {
        Kontaktliste a = new Kontaktliste();
        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));

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
   * @return Ein Vektor mit Kontaktliste-Objekten, die sämtliche Konten des
   *         betreffenden Kunden repräsentieren. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Kontaktliste> findByUser(int id) {
    Connection con = DBConnection.connection();
    Vector<Kontaktliste> result = new Vector<Kontaktliste>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, name FROM T_KONTAKTLISTE "
          + "WHERE id_user =" + id + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Kontaktliste-Objekt erstellt.
      while (rs.next()) {
        Kontaktliste a = new Kontaktliste();
        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));

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
  public Vector<Kontaktliste> findByUser (User user) {

    /*
     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByUser(user.getId());
  }

  /**
   * Einfügen eines <code>Kontaktliste</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Kontaktliste insert(Kontaktliste a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM T_KONTAKTLISTE ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * a erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        a.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO T_KONTAKTLISTE (id, name) " + "VALUES ("
            + a.getId() + "," + a.getName() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Kontaktlistes.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Kontaktliste-Objekts auch
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
  public Kontaktliste update(Kontaktliste a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE T_KONTAKTLISTE " + "SET name=\"" + a.getName()
          + "\" " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Kontaktliste a) zu wahren, geben wir a zurück
    return a;
  }

  /**
   * Löschen der Daten eines <code>Kontaktliste</code>-Objekts aus der Datenbank.
   * 
   * @param a das aus der DB zu löschende "Objekt"
   */
  public void delete(Kontaktliste a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM T_KONTAKTLISTE " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Konten (<code>Kontaktliste</code>-Objekte) eines Kunden.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Customer</code>
   * -Objekt gelöscht wird.
   * 
   * @param c das <code>Customer</code>-Objekt, zu dem die Konten gehören
   */
  public void deleteKontaktlisteOf(User c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM T_KONTAKTLISTE " + "WHERE user_id =" + c.getId());

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
  public User getUser(Kontaktliste a) {
    /*
     * Wir bedienen uns hier einfach des CustomerMapper. Diesem geben wir
     * einfach den in dem s-Objekt enthaltenen Fremdschlüssel für den
     * Kontoinhaber. Der CustomerMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return UserMapper.userMapper().findBy(a.getUser());
  }

}

