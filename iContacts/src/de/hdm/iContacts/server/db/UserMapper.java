package de.hdm.iContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.iContacts.shared.bo.User;

/**
 * Mapper-Klasse, die <code>User</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see CustomerMapper, TransactionMapper
 * @author Thies
 */
public class UserMapper {

  /**
   * Die Klasse UserMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see accountMapper()
   */
  private static UserMapper userMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected UserMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>UserMapper.accountMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>UserMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> UserMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>UserMapper</code>-Objekt.
   * @see accountMapper
   */
  public static UserMapper userMapper() {
    if (userMapper == null) {
      userMapper = new UserMapper();
    }

    return userMapper;
  }

  /**
   * Suchen eines Kontos mit vorgegebener Kontonummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Konto-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public User findByKey(int id) { //importieren aus shared, da user klasse exisier- "suchen", abfrage
    // DB-Verbindung holen
    Connection con = DBConnection.connection(); // db verbindung holen, holt verbindung für mapper
    try {
        // Leeres SQL-Statement (JDBC) anlegen
        Statement stmt = con.createStatement(); //abfrage generieren vom typ statement, objekt, benötigt man um statement zur db zu schicken

        // Statement ausfüllen und als Query an die DB schicken

        ResultSet rs = stmt.executeQuery("SELECT * FROM T_USER WHERE id=" + id);  //wird an db geschickt mit stm, ergebnis ist im resultset gespeichert, abfrage durchführen, immer in "
           //plus ist für die verknüpfung von id mit string (blau), rechte id ist id von findbykey

        /*
         * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
         * werden. Prüfe, ob ein Ergebnis vorliegt.
         */
        if (rs.next()) { //.next, erste zeile von resultset
          // Ergebnis-Tupel in Objekt umwandeln
          User a = new User();
          a.setId(rs.getInt("id"));
          a.setVorname(rs.getString("vorname"));
          a.setNachname(rs.getString("nachname"));
          a.setEMail(rs.getString("eMail"));
         
          return a;
          
        }
      }
      catch (SQLException e) { //fehlerbehandlung zb. verbindung zum server nicht möglich, ein Muss
    	  e.printStackTrace(); //fehlermdelung wird auf console ausgegeben
        return null;
      }

    return null;
  }

  /**
   * Auslesen aller Konten.
   * 
   * @return Ein Vektor mit User-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<User> findAll() { // alle datensätze rausziehen aus tabelle user
    Connection con = DBConnection.connection(); //wieder auf db verbindung zugreifen, in jeder methode neu

    // Ergebnisvektor vorbereiten
    Vector<User> result = new Vector<User>(); //leerer vektor

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT * FROM T_USER ORDER BY id"); //kein where notwendig
    		  
      // Für jeden Eintrag im Suchergebnis wird nun ein User-Objekt erstellt.
      while (rs.next()) { //schleife läuft durch, bis es keinen datensatz mehr gibt, gegenteil zu if
        User a = new User();
        a.setId(rs.getInt("id"));
        a.setVorname(rs.getString("vorname"));
        a.setNachname(rs.getString("nachname"));
        a.setEMail(rs.getString("eMail"));
        

        // Hinzufügen des neuen Objekts zum Ergebnisvektor, da mehrere objekte, nicht nur eins wie bei findbykey
        result.addElement(a); //vektor wird mit den erzeugten user-okjekten gefüllt
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
   * @return Ein Vektor mit User-Objekten, die sämtliche Konten des
   *         betreffenden Kunden repräsentieren. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
 
   
  public User findBy (User user) {

    /*
     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByKey(user.getId());
  }

  /**
   * Einfügen eines <code>User</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public User insert(User a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " //zeige die größte id in meiner db
          + "FROM T_USER ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * a erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        a.setId(rs.getInt("maxid") + 1); // wenn zb höchste id3, 3 plus 1= 4

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO T_USER (id, vorname, nachname, eMail) " + "VALUES ("
            + a.getId() + ",'" + a.getVorname() + "','" + a.getNachname() + "','" + a.getEMail() +"')"); //+ klammer im string am ende, strings in anführungszeichen
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Users.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des User-Objekts auch
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
  public User update(User a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE T_USER SET vorname= '"  + a.getVorname() + "', nachname = '" + a.getNachname() +"', eMail = '" + a.getEMail() + "' WHERE = " + a.getId());
    }
    // executeQuery für lesen, abfragen
    // executeUpdat für Ändern (delete auch)
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(User a) zu wahren, geben wir a zurück
    return a;
  }

  /**
   * Löschen der Daten eines <code>User</code>-Objekts aus der Datenbank.
   * 
   * @param a das aus der DB zu löschende "Objekt"
   */
  public void delete(User a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM T_USER " + "WHERE id=" + a.getId()); // nur eine angabe, da es die ganze zeile löscht

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Konten (<code>User</code>-Objekte) eines Kunden.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Customer</code>
   * -Objekt gelöscht wird.
   * 
   * @param c das <code>Customer</code>-Objekt, zu dem die Konten gehören
   */
  
}

