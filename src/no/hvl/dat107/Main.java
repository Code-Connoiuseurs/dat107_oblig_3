package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	private static final AnsattDAO ansattDao = new AnsattDAO();
	private static final Scanner scanner = new Scanner(System.in);
	private static final AvdelingDAO avdelingDAO = new AvdelingDAO();
	
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
					+ "6: legg til ny ansatt\n"
					+ "7: finn avdeling med id");
			
			String valg = scanner.nextLine();
			try {
			switch (valg) {
				case "q":
					scanner.close();
					programKjører = false;
					System.out.println("Program lukket");
					break;
				case "1":
					System.out.print("Ansatt id: ");
					System.out.println(ansattDao.finnAnsattMedId(Integer.parseInt(scanner.nextLine())));
					break;
				case "2":
					System.out.print("Ansatt brukernavn: ");
					System.out.println(ansattDao.finnAnsattMedBrukernavn(scanner.nextLine()));
					break;
				case "3":
					System.out.println("Alle ansatte: ");
					System.out.println(ansattDao.hentAlleAnsatte());
					break;
				case "4":
					System.out.print("Ansatt id: ");
					int idStilling = Integer.parseInt(scanner.nextLine());
					System.out.print("Ny stilling: ");
					String nyStilling = scanner.nextLine();
					System.out.println(ansattDao.oppdaterStilling(idStilling, nyStilling));
					break;
				case "5":
					System.out.print("Ansatt id: ");
					int idLonn = Integer.parseInt(scanner.nextLine());
					System.out.print("Ny lønn: ");
					
					Double nyLonn = Double.parseDouble(scanner.nextLine());
					System.out.println(ansattDao.oppdaterLonn(idLonn, nyLonn));

					
					break;
				case "6":			
					System.out.print("Brukernavn: ");
					String brukernavn = scanner.nextLine().toLowerCase();
					System.out.print("Fornavn: ");
					String fornavn = scanner.nextLine();
					System.out.print("Etternavn: ");
					String etternavn = scanner.nextLine();
					
					LocalDate ansettelsesdato = LocalDate.now(); // Foresnkling
					
					System.out.print("Stilling: ");
					String stilling = scanner.nextLine();
					
					System.out.print("Lønn: ");
					Double lonn = Double.parseDouble(scanner.nextLine());
					
					System.out.print("AvdelingsId: ");
					int avdelingsid6 = Integer.parseInt(scanner.nextLine());
					Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdelingsid6);
					
					if (avdeling == null) break;
					
					System.out.println(ansattDao.lagreNyAnsatt(new Ansatt(
							brukernavn, fornavn, etternavn, ansettelsesdato, stilling, lonn, avdeling
					)));
					
					break;
				case "7":
					System.out.print("Avdelingsid:");
					int avdelingsid7 = Integer.parseInt(scanner.nextLine());
					System.out.println(avdelingDAO.finnAvdelingMedId(avdelingsid7));
					break;
				default:
					System.out.println("Ugyldig valg, prøv igjen");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Id/lønn kan kun bestå av tall. Prøv igjen.");
			} 
			}
			
		}
	}
