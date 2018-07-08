package de.hdm.iContacts.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.appengine.api.utils.SystemProperty;


//
//public class DBConnection {
//	/**
//	 * 
//	 * Die Klasse DBConnection wird nur einmal instantiiert (<b>Singleton</b>). Diese Variable ist durch den Bezeichner
//	 * <code>static</code> nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden und speichert die
//	 * einzige Instanz dieser Klasse.
//	 */
//	private static Connection con = null;
//
//	public static Connection connection() {
//
//		// Prüfen, ob es bisher noch kein Objekt gibt, in der die Verbindung zur DB gespeichert sit
//		if (con == null) {
//			try {
//				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//					/*
//					 * Wenn die Applikation bereits auf App Engine deployt wurde, kann direkt darauf zugegriffen werden,
//					 * ohne angabe eines Passworts, denn die Zugriffsrechts wurden bereits in der Konsole eingeräumt
//					 */
//					Class.forName("com.mysql.jdbc.GoogleDriver");
//					con = DriverManager.getConnection("");
//				} else {
//					/*
//					 * Wenn von einem anderen Netzwerk aus darauf zugegriffen werden soll, passiert das mit diesen
//					 * Daten. Hinweis: Damit das klappt, muss die aktuelle IP-Adresse des Computers, von den man das
//					 * machen möchte, in der Konsole auf die Whitelist gesetzt werden. Dieser Eintrag ist dann jeweils
//					 * nur 24h gültig. Wenn das benötigt wird, bitte an Julius Renner wenden.
//					 */
//					Class.forName("com.mysql.jdbc.Driver");
//					con = DriverManager.getConnection("jdbc:mysql://localhost:3308/itprojektss18?user=root&password=");
//				} //speicheradresse von db angeben für db zugriff //
//				   //jdbc:mysql://127.0.0.1:3306/db_icontacts?user=root&password=adminadmin");
//
//				// Abfangen verschiedener Exceptions, falls etwas schiefgeht.
//			} catch (SQLException e1) {
//				con = null;
//				e1.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		// Rückgabe der Verbindung in der Variable <code>con</code>.
//		return con;
//	}
//}
//
//

public class DBConnection { //verbindung zur datenbank

    
    private static Connection con = null; //wieso resultSet nicht = null und statement

    
    private static String googleUrl = "jdbc:google:mysql://itprojektss18:europe-west:itprojektinstanz/itprojektdb?user=root&password=1234";
    private static String localUrl = "jdbc:mysql://localhost:3308/itprojektss18?user=root&password=";
    //private static String localUrl2 = "jdbc:mysql://localhost:3306/db_icontacts?user=root&password=adminadmin";



   
    public static Connection connection() {
        
        if (con == null) {
            String url = null;
            try { //versuch, wenn e gut läuf, gehts nicht zu catch
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) { //überprüft ob es auf app engine läuft
                    
                    Class.forName("com.mysql.jdbc.GoogleDriver"); //google treiber
                    url = googleUrl;
                } else {
                   
                    Class.forName("com.mysql.jdbc.Driver"); //lokaler driver
                    url = localUrl;
                }
                
                con = DriverManager.getConnection(url); //punkt aufruf erklären, klasse
            } catch (Exception e) { //zb passort falsch etc, sqlserver nicht bereit/ gestartet
                con = null;
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        
        

        
        return con; //con object zurückscicke, mpper klasse nimmt con object
    }

}
