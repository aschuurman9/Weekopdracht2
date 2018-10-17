package weekopdracht2;

import java.util.ArrayList;
import java.util.Scanner;

public class YathzeeSpel {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Dobbelsteen> dobbelstenenrij = new ArrayList<>();
	int[] blokkeerarray = { 0, 0, 0, 0, 0 };
	Worp worp = new Worp();
	Speler speler = new Speler();

	YathzeeSpel() {
		System.out.println("Welkom bij Yathzee!\nDruk op Enter om te beginnen.");
		for (int i = 0; i < 5; i++) {
			Dobbelsteen dobbelsteen = new Dobbelsteen();
			dobbelstenenrij.add(dobbelsteen);
		}
	}

	void spelen() {
		String invoer = scanner.nextLine();
		boolean doorSpelen = true;

		while (doorSpelen) {
			switch (invoer) {
			case "":
				int index = 0;
				for (Dobbelsteen ds : dobbelstenenrij) {
					if (blokkeerarray[index] == 0) {
						ds.ogen = ds.werpen();
						worp.worpArray[index] = ds.ogen;
						index++;
					} else {
						index++;
					}
				}
				worp.WorpUitslag(dobbelstenenrij);
				resetBlokkeerarray();
				vasthouden();
				speler.toevoegenAanWorpGeschiedenis(worp);
				speler.printWorpGeschiedenis();

				invoer = scanner.nextLine();
				break;
			case "q":
				System.out.println("Stoppen");
				doorSpelen = false;
				break;
			default:
				System.out.println("Dit is geen geldige invoer. Kies opnieuw");
				invoer = scanner.nextLine();
				break;
			}
		}
	}

	int[] vasthouden() {
		System.out.println("Welke posities wilt u vasthouden? 0 voor geen anders bijv 124");
		System.out.println("INVOER: ");
		String invoer = scanner.nextLine();
		for (int i = 0; i < invoer.length(); i++) {
			int positie = Integer.parseInt(Character.toString(invoer.charAt(i)));
			if (positie > 0 & positie < 7) {
				blokkeerarray[positie - 1] = 1;
			}
		}
		System.out.println("Vasthouden: ");
		for (int i = 0; i < blokkeerarray.length; i++) {
			System.out.print(blokkeerarray[i] + " ");
		}
		System.out.println();
		return blokkeerarray; // doe hier niets mee
	}


	void resetBlokkeerarray() {
		for (int i = 0; i < blokkeerarray.length - 1; i++) {
			blokkeerarray[i] = 0;
		}
	}

}
