package weekopdracht2;

import java.util.ArrayList;
import java.util.Scanner;

public class YathzeeSpel {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Dobbelsteen> dobbelstenenrij = new ArrayList<>();
	int[] blokkeerarray = { 0, 0, 0, 0, 0 };
	Worp worp = new Worp();

	YathzeeSpel() {
		System.out.println("Welkom bij Yathzee!\nMet hoeveel spelers wil je spelen?");
		int aantalSpelers = scanner.nextInt();
		for (int i = 0; i < aantalSpelers; i++) {
			System.out.println("Voer de naam van de speler in: ");
			String naam = scanner.next();
			Speler speler = new Speler(naam);
			Speler.spelers.add(speler);
			System.out.println();
		}
		for (int i = 0; i < 5; i++) {
			Dobbelsteen dobbelsteen = new Dobbelsteen();
			dobbelstenenrij.add(dobbelsteen);
		}
	}

	void spelen() {
		String invoer = scanner.nextLine();
		boolean doorSpelen = true;
		
		System.out.println("Het spel begint: ");
		while (doorSpelen) {
			switch (invoer) {
			case "":
				for (int i = 0; i < Speler.spelers.size(); i++) {
					System.out.println("\n" + Speler.spelers.get(i).naam + " is aan de beurt.");
					System.out.println("======================================================");
					dobbelen(Speler.spelers.get(i));
					invoer = scanner.nextLine();
				}
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

	void dobbelen(Speler speler) {
		int aantalKeerGooien = 0;
		do {
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
			if (aantalKeerGooien < 2 ) {
				vasthouden();
			} else {
				speler.scoreblad.printScoreKaart();
				showMenuScoreNoteren();
				int invoer = scanner.nextInt();
				verwerkKeuzeScoreNoteren(invoer, speler);
				voegScoreAanScoreKaartToe(speler, invoer-1);
				speler.scoreblad.printScoreKaart();
			}
			speler.toevoegenAanWorpGeschiedenis(worp);
//			speler.printWorpGeschiedenis();
			aantalKeerGooien++;
		} while (aantalKeerGooien < 3);
		System.out.println("Totaal aantal punten: " + speler.scoreblad.berekenTotaalScore());
		aantalKeerGooien = 0;
		resetBlokkeerarray();
	}

	int[] vasthouden() {
		System.out.println("Welke posities wilt u vasthouden? 0 voor geen anders bijv 124");
		System.out.println("INVOER: ");
		String invoer = scanner.nextLine();
		for (int i = 0; i < invoer.length(); i++) {
			int positie = Integer.parseInt(Character.toString(invoer.charAt(i)));
			if (positie > 0 & positie < 6) {
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
		for (int i = 0; i < blokkeerarray.length; i++) {
			blokkeerarray[i] = 0;
		}
	}
	
	void showMenuScoreNoteren() {
		System.out.println("Hoe wil je de score noteren?");
		System.out.println("Kies: ");
		System.out.println("1.\talle enen");
		System.out.println("2.\talle tweeen");
		System.out.println("3.\talle drieen");
		System.out.println("4.\talle vieren");
		System.out.println("5.\talle vijven");
		System.out.println("6.\talle zessen");
		System.out.println("7.\tthree of a kind");
		System.out.println("8.\tcarre");
		System.out.println("9.\tkleine straat");
		System.out.println("10.\tgrote straat");
		System.out.println("11.\tyathzee");
		System.out.println("12.\tchance");
	}
	
	void verwerkKeuzeScoreNoteren(int keuze, Speler speler) {
		switch (keuze){
			case 1:
				speler.scoreblad.berekenScoreEenenEnZessen(worp, 1);
				break;
			case 2:
				speler.scoreblad.berekenScoreEenenEnZessen(worp, 2);
				break;
			case 3:
				speler.scoreblad.berekenScoreEenenEnZessen(worp, 3);
				break;
			case 4:
				speler.scoreblad.berekenScoreEenenEnZessen(worp, 4);
				break;
			case 5:
				speler.scoreblad.berekenScoreEenenEnZessen(worp, 5);
				break;
			case 6:
				speler.scoreblad.berekenScoreEenenEnZessen(worp, 6);
				break;
			case 7:
				speler.scoreblad.berekenScoreThreeOfAKindOfCarre(worp, 3);
				break;
			case 8:
				speler.scoreblad.berekenScoreThreeOfAKindOfCarre(worp, 4);
				break;
			case 9:
				speler.scoreblad.berekenScoreStraat(worp, 2);
				break;
			case 10:
				speler.scoreblad.berekenScoreStraat(worp, 1);
				break;
			case 11:
				speler.scoreblad.berekenScoreYathzee(worp);
				break;
			case 12:
				speler.scoreblad.berekenScoreChance(worp);
				break;
		} 
		System.out.println("score: " + speler.scoreblad.score);
	}
	
	void voegScoreAanScoreKaartToe(Speler speler, int positie) {
			if (speler.scoreblad.kaart[positie] == -1) {
				speler.scoreblad.kaart[positie] = speler.scoreblad.score;
			} else {
				System.out.println("Deze optie is niet mogelijk. Kies opnieuw. ");
				int invoer = scanner.nextInt();
				verwerkKeuzeScoreNoteren(invoer, speler);
				voegScoreAanScoreKaartToe(speler, invoer-1);
			}
	}

}
