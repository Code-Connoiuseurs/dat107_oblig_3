package Database_Script;

import java.util.List;
import java.util.Scanner;

public class Main_min {

	private static final AnsattDAO_min ansattDAO_min = new AnsattDAO_min();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("q : stop programmet. \n" + "1 : Finn ansatt ved bruk av id. \n"
				+ "2 : Finn ansatt ved bruk av brukernavn. \n" + "3 : Liste av alle anstte. \n"
				+ "4 : Oppdater en ansatt sin stilling. \n" + "5 : Oppdater en ansatt sin lønn. \n"
				+ "6 : legg inn en ny ansatt.");

		String valgt = scanner.nextLine();
		try {

			switch (valgt) {
			case "q":
				scanner.close();
				System.out.println("Programmet har stoppet");
				break;

			case "1":
				System.out.println("Ansatt id: ");
				int ansattId = Integer.parseInt(scanner.nextLine());
				Ansatt_min ansatt = ansattDAO_min.finnAnsattMedId(ansattId);
				if (ansatt != null) {
					ansatt.skrivUt();
				} else {
					System.out.println("Ansatt med Id: " + ansattId + " finnes ikke");
				}
				break;
			case "2":
				System.out.println("Ansatt brukernavn / initialer: ");
				Ansatt_min ansattbruker = ansattDAO_min.finnAnsattMedInitial(scanner.nextLine());
				if (ansattbruker != null) {
					ansattbruker.skrivUt();
				} else {
					System.out.println("Ansatt med brukernavn eller initialer: " + ansattbruker + " ble ikke funnet");
				}
				break;

			case "3":
				System.out.println("Liste av ansatte:  \n");
				List<Ansatt_min> ansatteListe = ansattDAO_min.listeAvAlleAnsatte();

				if (ansatteListe != null) {
					for(Ansatt_min a : ansatteListe) {
					a.skrivUt();
					System.out.println();
					}
				}
				break;
				
			case "4":
				System.out.println("Ansatt id: ");
				
				int idAnsatt = Integer.parseInt(scanner.nextLine());
				System.out.println("Ny stilling: ");
				
				String nyStilling = scanner.nextLine();
				System.out.println(ansattDAO_min.OppdaterStilling(idAnsatt, nyStilling));
				break;
				
			case "5":
				break;
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Prøv igjen.");
		}
	}
}
