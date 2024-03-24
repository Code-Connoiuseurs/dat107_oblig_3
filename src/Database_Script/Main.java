package Database_Script;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final AnsattDAO ansattDAO = new AnsattDAO();
	private static final Scanner scanner = new Scanner(System.in);
	private static final AvdelingDAO avdelingDAO = new AvdelingDAO();

	public static void main(String[] args) {
		boolean programmetkjører = true;

		while (programmetkjører) {

			System.out.println("\nq stop programmet. \n" + "1 : Finn ansatt ved bruk av id. \n"
					+ "2 : Finn ansatt ved bruk av brukernavn. \n" + "3 : utlisting av alle Ansatt. \n"
					+ "4 : Oppdater stilling til ansatt. \n" + "5 : Oppdater lønn til Ansatt. \n"
					+ "6 : Legg til ny ansatt. \n"
					+ "7 : Finn avdenling ved bruk av id. \n"
					+ "8 : Utlisting av ansatte i avdeling ved bruk av id. \n"
					+ "9 : Oppdater avdeling til ansatt"  );

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
					
					System.out.println("avdeling id: ");
					int avdelingId = Integer.parseInt(scanner.nextLine());
					Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdelingId);
					
					Ansatt nyAnsatt = new Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato,
							stilling, lonn, avdeling);
					System.out.println(ansattDAO.leggTilAnsatt(nyAnsatt));
					break;
				
				case "7": 
					System.out.println("Avdeling ID: ");
					System.out.println(avdelingDAO.finnAvdelingMedId(Integer.parseInt(scanner.nextLine())));
					break;
					
				case "8": 	
					System.out.println("Avdeling ID: ");
					List<Ansatt> ansattInfo = avdelingDAO.utlistingAvAlleIAvdeling(Integer.parseInt(scanner.nextLine()));
					for(Ansatt a : ansattInfo) {
						System.out.println("\n" + a);
					}
					break;
				case "9": 
					System.out.println("Ansatt Id: ");
					int ansattId4 = Integer.parseInt(scanner.nextLine());

					System.out.println("Ny avdeling id: ");
					int avdelingId4 = Integer.parseInt(scanner.nextLine());
					
					Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId4);
					Avdeling nyAvdeling = avdelingDAO.finnAvdelingMedId(avdelingId4);
					
					if(ansatt != null && nyAvdeling != null) {
						Avdeling gammleAvd = ansatt.getAvdeling();
						
					if(gammleAvd != null) {
						gammleAvd.fjern(ansatt);
						avdelingDAO.OppdaterAvdeling(gammleAvd);
						}
					
						nyAvdeling.leggTil(ansatt);
						avdelingDAO.OppdaterAvdeling(nyAvdeling);
						System.out.println(ansattDAO.oppdaterAnsattAvdeling(ansattId4, avdelingId4));
					} else {
						System.out.println("Avdelingen ble ikke oppdatert");
					}
				
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
