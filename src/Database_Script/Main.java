package Database_Script;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final AnsattDAO ansattDAO = new AnsattDAO();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean programmetkjører = true;

		while (programmetkjører) {

			System.out.println("\nq stop programmet. \n" + "1 : Finn ansatt ved bruk av id. \n"
					+ "2 : Finn ansatt ved bruk av brukernavn. \n" + "3 : utlisting av alle Ansatt. \n"
					+ "4 : Oppdater stilling til ansatt. \n" + "5 : Oppdater lønn til Ansatt. \n"
					+ "6 : Legg til ny ansatt. \n");

			String valgt = scanner.nextLine();
			try {
				switch (valgt) {

				case "q":
					scanner.close();
					programmetkjører = false;
					System.out.println("Programmet har stoppet");
					break;

				case "1":
					System.out.println("Ansatt id: ");
					System.out.println(ansattDAO.finnAnsattMedId(Integer.parseInt(scanner.nextLine())));
					break;

				case "2":
					System.out.println("Brukernavn: ");
					System.out.println(ansattDAO.finnAnsattMedBNavn(scanner.nextLine()));
					break;

				case "3":
					System.out.println("Utlisting av alle ansatt: ");
					List<Ansatt> listeAvAnsatt = ansattDAO.utlistingAvAlleAnsatt();

					if (listeAvAnsatt != null) {
						for (Ansatt a : listeAvAnsatt) {
							System.out.println("\n" + a.toString());
						}
					}
					break;

				case "4":
					System.out.println("Ansatt Id: ");
					int id = Integer.parseInt(scanner.nextLine());

					System.out.println("Ny stilling: ");
					String nyStilling = scanner.nextLine();
					System.out.println(ansattDAO.oppdaterStilling(nyStilling, id));
					break;

				case "5":
					System.out.println("Ansatt Id: ");
					int id1 = Integer.parseInt(scanner.nextLine());

					System.out.println("Ny Lønn: ");
					double NyLonn = Double.parseDouble(scanner.nextLine());
					System.out.println(ansattDAO.oppdaterLonn(NyLonn, id1));
					break;
					
				case "6":
					System.out.println("Brukernavn: ");
					String brukernavn = scanner.nextLine().toLowerCase();
					System.out.println("Fornavn: ");
					String fornavn = scanner.nextLine();
					System.out.println("Etternavn: ");
					String etternavn = scanner.nextLine();
					
					LocalDate ansettelsesdato = LocalDate.now();
					System.out.println("Stilling: ");
					String stilling = scanner.nextLine();
					System.out.println("Lønn: ");
					Double lonn = Double.parseDouble(scanner.nextLine());
					
				//	System.out.println("avdeling id: ");
				//	int avdelingId = avdelingDAO
					
					Ansatt nyAnsatt = new Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato,
							stilling, lonn, null);
					System.out.println(ansattDAO.leggTilAnsatt(nyAnsatt));
					break;
					

				default:
					System.out.println("\nUgyldig, prøv igjen. \n");
					break;
				}
			} catch (NumberFormatException e) {
			}
		}
	}
}
