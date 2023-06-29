package ver_csvP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ver_dbp.ver_db_load;

public class ver_csv_kundeni {

	public ver_csv_kundeni() throws FileNotFoundException {
		String pfad ="D:\\Schulung\\csv\\ver_dat_csv\\kunden.csv";
		String pfada = "D:\\Schulung\\csv\\ver_dat_csv\\";
		PrintStream output=null;
		
		Scanner scanner = new Scanner(new File(pfad)).useDelimiter(";");
		//-----------------------------------------
				// Erstellen der Ausgabedatei kunden_imp.csv
				//------------------------------------------
		          pfada = pfada + "kunden_imp.csv"; //Pfad vervollständigen 
				try {
					 //output = new PrintStream(pfada+"kunden_imp.csv");
					 output = new PrintStream(pfada);
				} catch (FileNotFoundException e) {
					System.out.println("Pfad "+pfada +" nicht gefunden");
					e.printStackTrace();
				} 
				String header = scanner.nextLine(); //lesen der Überschrift aus kunden.csv
				String header_neu;    // für kunden_imp.csv
				String neu = "Kunden_Nr", m1 = "Name", m2 = "Vorname", m3 = "Geb_Dat",
				        m4 ="timestamp",sep = ";";
				header_neu = neu + sep + m1 + sep + m2 + sep + m3 + sep+ m4;
				output.println(header_neu);    //Überschrift für Ergebnis.csv	
				
				// -----------------------------------------------
				// Zeilenweises einlesen und verarbeiten der Datei
				// "kunden.csv"
				// --------------------
				while (scanner.hasNextLine()) {
					Scanner sc = new Scanner(scanner.nextLine()).useDelimiter(";");
					
					int kundnri = sc.nextInt();
					String namei = sc.next();
					String vornamei = sc.next();
					String geb_dati = sc.next();
					String timest=timestamp();      //Timestamp aufrufen und zurückgeben
					//Erstellen der Datei 
					output.println(kundnri +";"+namei+";"+vornamei+";"+geb_dati
							+";"+ timest);
				}	
				output.close();
				scanner.close();
				System.out.println("Datei kunden_imp.csv erstellt");
			
				   // Aufruf ver_db_load  ver_kundstamm füllen 
				         ver_db_load vdl = new ver_db_load (pfada);
	}

	private String timestamp() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String timeStamp = date.format(new Date());
        System.out.println("Zeitstempel : "+timeStamp);
		return timeStamp;
	}

	
}
	
