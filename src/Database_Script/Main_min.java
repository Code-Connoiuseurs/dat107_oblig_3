package Database_Script;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main_min {

	private static final AnsattDAO_min ansattDAO_min = new AnsattDAO_min();
	private static final Scanner scanner = new Scanner(System.in);
	private static final AvdelingDAO_min avdelingDAO_min = new AvdelingDAO_min();

	public static void main(String[] args) {
		boolean progKjører = true;

		while (progKjører) {

			System.out.println("\nq : stop programmet. \n" + "1 : Finn ansatt ved bruk av id. \n"
					+ "2 : Finn ansatt ved bruk av brukernavn. \n" + "3 : Liste av alle anstte. \n"
					+ "4 : Oppdater en ansatt sin stilling. \n" + "5 : Oppdater en ansatt sin lønn. \n"
					+ "6 : legg inn en ny ansatt. \n" + "7 : finn avdeling med id.");

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
						System.out
								.println("Ansatt med brukernavn eller initialer: " + ansattbruker + " ble ikke funnet");
					}
					break;

				case "3":
					System.out.println("Liste av ansatte:  \n");
					List<Ansatt_min> ansatteListe = ansattDAO_min.listeAvAlleAnsatte();

					if (ansatteListe != null) {
						for (Ansatt_min a : ansatteListe) {
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
					System.out.println(ansattDAO_min.oppdaterStilling(idAnsatt, nyStilling));
					break;

				case "5":
					System.out.println("Ansatt id: ");

					int idAn = Integer.parseInt(scanner.nextLine());
					System.out.println("Ny lønn: ");

					double nyLonn = Double.parseDouble(scanner.nextLine());
					System.out.println(ansattDAO_min.oppdaterLonn(idAn, nyLonn));
					break;

				case "6":

					System.out.println("Brukernavn: ");
					String brukernavn = scanner.nextLine();
					System.out.println("Fornavn: ");
					String fornavn = scanner.nextLine();
					System.out.println("Etternavn: ");
					String etternavn = scanner.nextLine();

					LocalDate ansett_dato = LocalDate.now();

					System.out.println("Stilling: ");
					String stilling = scanner.nextLine();
					System.out.println("Lønn: ");
					double lonn = Double.parseDouble(scanner.nextLine());

					Ansatt_min nyAnsatt = new Ansatt_min(brukernavn, fornavn, etternavn, ansett_dato, stilling, lonn,
							null); // null blir stilling

					System.out.println();
					nyAnsatt.skrivUt();
					System.out.println();
					break;
					
				case "7": 
					System.out.println("Avdeling id: ");
					int avdelingId = Integer.parseInt(scanner.nextLine());
					Avdeling_min avdeling = avdelingDAO_min.finnAvdelingMedId(avdelingId);
					avdeling.skrivUt();
					break;
					
				default:
					System.out.println("\nUgyldig valg, prøv igjen \n");
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("Prøv igjen.");
			}
		}
		scanner.close();
	}
}
