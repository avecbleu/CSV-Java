package csvp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ergebnis_csv { 

	public static void main(String[] args) throws FileNotFoundException {
		String pfad ="D:\\Schulung\\csv\\Mehrwertsteuer.csv";
		String pfada = "D:\\Schulung\\csv\\";
		Scanner scanner = new Scanner(new File(pfad)).useDelimiter(";");
		//-----------------------------------------
		// Erstellen der Ausgabedatei Ergebnis.csv
		//------------------------------------------
		PrintStream output = new PrintStream(pfada+"Ergebnis.csv");     
		// -----------------------------------
		// Ausgabe der Kopfzeile der csv-Datei
		// -----------------------------------
		String header = scanner.nextLine(); //lesen der Überschrift aus Mehrwertsteuer.csv
		String header_neu;    // für Ergebnis.csv
		String n = "Nettobetrag", m1 = "Mehrwertsteuersatz", m2 = "Mehrwertsteuer", b = "Bruttobetrag", w = "Waehrung",
				sep = ";";
		header_neu = n + sep + m1 + sep + m2 + sep + b + sep + w;
		output.println(header_neu);    //Überschrift für Ergebnis.csv
		double netto, brutto, steuer;
		int mwst;
		String brutto_string, waehrung, steuer_string,netto_string;
		// -----------------------------------------------
		// Zeilenweises einlesen und verarbeiten der Datei
		// "Mehrwertsteuer.csv"
		// --------------------
		while (scanner.hasNextLine()) {
			Scanner sc = new Scanner(scanner.nextLine()).useDelimiter(";");
			netto = sc.nextDouble();
			mwst = sc.nextInt();
			sc.next();
			waehrung = sc.next();
			brutto = netto + netto * mwst / 100.0; // Berechnung Bruttobetrag 
			brutto_string = new DecimalFormat("0.00").format(brutto);  // runden 
			netto_string =  new DecimalFormat("0.00").format(netto);  // runden  neu
			steuer = netto * mwst / 100.0;    //Berechnung der Mehrwertstuer
			steuer_string = new DecimalFormat("0.00").format(steuer); //runden
			//---------------------------------------
			// Ausgabe in die CSV-Datei Ergebnis.csv
			//---------------------------------------
			output.println(netto_string + ";" + mwst + ";" + steuer_string + ";" + brutto_string + ";" + waehrung);
		}
		     System.out.println("Datei erstellt" );
		    output.close();  //Ergebnis.csv
		    scanner.close();
	}
}
