package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	private static final AnsattDAO ansattDao = new AnsattDAO();
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean programKjører = true;		
		while(programKjører) {
			
			System.out.println("\nVennligst oppgi et valg:\n"
					+ "q: lukk programmet\n"
					+ "1: finn ansatt med id\n"
					+ "2: finn ansatt med brukernavn\n"
					+ "3: hent alle ansatte\n"
					+ "4: oppdater ansatt sin stilling\n"
					+ "5: oppdater ansatt sin lønn\n"
					+ "6: legg til ny ansatt");
			
			String valg = scanner.nextLine();
			
			switch (valg) {
				case "q":
					scanner.close();
					programKjører = false;
					System.out.println("Program lukket");
					break;
				case "1":
					System.out.print("Ansatt id: ");
					System.out.println(ansattDao.finnAnsattMedId(scanner.nextInt()));
					break;
				case "2":
					System.out.print("Ansatt brukernavn: ");
					System.out.println(ansattDao.finnAnsattMedBrukernavn(scanner.nextLine()));
					break;
				case "3":
					System.out.print("Alle ansatte: ");
					System.out.println(ansattDao.hentAlleAnsatte());
					break;
				case "4":
					System.out.print("Ansatt id: ");
					int idStilling = scanner.nextInt();
					System.out.print("Ny stilling: ");
					String nyStilling = scanner.nextLine();
					System.out.println(ansattDao.oppdaterStilling(idStilling, nyStilling));
					break;
				case "5":
					System.out.print("Ansatt id: ");
					int idLonn = scanner.nextInt();
					System.out.print("Ny lønn: ");
					String nyLonn = scanner.nextLine();
					System.out.println(ansattDao.oppdaterStilling(idLonn, nyLonn));
					break;
				case "6":
					System.out.println("TODO");
					
					System.out.print("Brukernavn: ");
					String brukernavn = scanner.nextLine();
					System.out.print("Fornavn: ");
					String fornavn = scanner.nextLine();
					System.out.print("Etternavn: ");
					String etternavn = scanner.nextLine();
					
					LocalDate ansettelsesdato = LocalDate.now(); // Foresnkling
					
					System.out.print("Stilling: ");
					String stilling = scanner.nextLine();
					
					System.out.print("Lønn: ");
					Double lonn = scanner.nextDouble();
					
					System.out.println(ansattDao.lagreNyAnsatt(new Ansatt(
							brukernavn, fornavn, etternavn, ansettelsesdato, stilling, lonn
					)));
					
					break;
				default:
					System.out.println("Ugyldig valg, prøv igjen");
					break;
			}
			
		}
	}
}