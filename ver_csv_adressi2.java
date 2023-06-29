package ver_csvP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import interfacep.DB_zugriff;
import ver_dbp.ver_db_load;
import ver_dbp.ver_db_load_adresse;

public class ver_csv_adressi2 implements DB_zugriff{

	public ver_csv_adressi2() throws FileNotFoundException {
		String pfad ="D:\\Schulung\\csv\\ver_dat_csv\\kunden.csv";
		//String pfada = "D:\\Schulung\\csv\\ver_dat_csv\\";
		PrintStream output=null;
		 Connection conn = null; 
		 try {
             System.out.println("* Treiber laden");
             
           //  Class.forName("com.mysql.cj.jdbc.Driver");  //neu
         } catch (Exception e) {
             System.err.println("Treiber kann nicht geladen werden!!");
             e.printStackTrace();
         }
		
		Scanner scanner = new Scanner(new File(pfad)).useDelimiter(";");
		//-----------------------------------------
				
				String header = scanner.nextLine(); //lesen der Überschrift aus kunden.csv
			
				
				// -----------------------------------------------
				// Zeilenweises einlesen und verarbeiten der Datei
				// "kunden.csv"
				// --------------------
				while (scanner.hasNextLine()) {
					Scanner sc = new Scanner(scanner.nextLine()).useDelimiter(";");
					
					//sc.next();    //Key
					int kundnri = sc.nextInt();
					sc.next();   //Name überspringen
					sc.next();   //Vorname "
					sc.next();   //Geb_dat "
					String strasse = sc.next();
					String hausnr= sc.next();
					String plz = sc.next();
					String ort = sc.next();
					String lkz = sc.next();
					
					String timest=timestamp();      //Timestamp aufrufen und zurückgeben
					//Erstellen der Datei 
				//	output.println(" "+";"+strasse+";"+hausnr+";"+plz+";"+ort+";"+lkz
				//			       +";"+kundnri+";"+ timest);
					try { 
			        	 System.out.println("* Verbindung aufbauen");
			    	    String url = "jdbc:mysql://"+hostname+":"+port+"/"+dbname; 
			    	    conn =  (Connection) DriverManager.getConnection(url, user, password);
			    	    // ***** Verbindung
			    	 
			            Statement stmt = (Statement) conn.createStatement(); 

					String sqlCommand ="Insert INTO ver_adresse (V_Strasse, V_Hausnr,"
					+ "V_PLZ, V_Ort,V_LKZ,V_Kundnr,V_timestamp)" 
					+ "VALUES("+ "'" + strasse + "'" +"," + "'" + hausnr + "'" + ","
					+ "'" + plz + "'" + "," + "'" + ort   + "'" + ","
					+ "'" + lkz + "'" + "," + "'" + kundnri + "'" + ","
					+"NOW()" + ")";
					System.out.println(sqlCommand);
					((java.sql.Statement) stmt).executeUpdate(sqlCommand); // da nur geschrieben wird
													// executeUpdate

					// **beenden Eingabe
					System.out.println("* Statement beenden");
					} catch (SQLException sqle) {
						System.out.println("SQLException: " + sqle.getMessage());
						System.out.println("SQLState: " + sqle.getSQLState());
						System.out.println("VendorError: " + sqle.getErrorCode());
						sqle.printStackTrace();
					}
				}	
				//output.close();
				scanner.close();
				//System.out.println("Datei adresse_imp.csv erstellt");
			
				  
	}

	private String timestamp() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String timeStamp = date.format(new Date());
        System.out.println("Zeitstempel : "+timeStamp);
		return timeStamp;
	}

	
}
	
