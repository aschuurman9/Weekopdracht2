package weekopdracht2;

import java.util.ArrayList;
import java.util.Arrays;

/*Punten score:
*	Van de enen naar de zessen:
	Gooi één of meer enen, tel ze op en vul de score in in het speciale enen-vakje; 
*	Three of a kind:
	Gooi drie dezelfde nummers en twee willekeurige nummers en telt hiervan het totaal op als score 
*	Carré:
	Gooi vier dezelfde nummers en 1 willekeurig ander nummer en telt hiervan het totaal op als score
*	Full house:
	Gooi twee dezelfde en drie dezelfde nummers en noteer 25 als score 
*	Kleine Straat:
	Gooi 4 opvolgende nummers en noteer 30 in het vakje Kleine Straat
*	Grote Straat:
	Gooi 5 opvolgende nummers en noteer 40 in het vakje Grote Straat
*	Yahtzee:
	Gooi 5 dezelfde nummers en noteer 50 in het vakje Yahtzee
*	Chance:
	totaal alle dobbelstenen
*/

public class Scoreblad {

	int score;
	int somScore;
	int scoreEnen, scoreTweeen, scoreDrieen, scoreVieren, scoreVijven, scoreZessen, scoreThreeOfAKind, scoreCarre,
			scoreKleineStraat, scoreGroteStraat, scoreYathzee, scoreChance;
	int[] kaart = { scoreEnen, scoreTweeen, scoreDrieen, scoreVieren, scoreVijven, scoreZessen, scoreThreeOfAKind,
			scoreCarre, scoreKleineStraat, scoreGroteStraat, scoreYathzee, scoreChance };
	int totaalScore = 0;

	Scoreblad() {
		kaartVullen();
	}

	void kaartVullen() {
		for (int i = 0; i < kaart.length; i++) {
			kaart[i] = -1;
		}
	}

	int berekenSomScore(Worp w) {
		somScore = 0;
		for (int i = 0; i < w.worpArray.length; i++) {
			somScore += w.worpArray[i];
		}
		return somScore;
	}

	int berekenScoreEenenEnZessen(Worp w, int ogenGegooid) {
		int counter = 0;
		score = 0;
		for (int i = 0; i < w.worpArray.length; i++) {
			if (w.worpArray[i] == ogenGegooid) {
				score = score + ogenGegooid;
				counter++;
			}
		}
		if (counter < 3) {
			score = 0;
		}
		return score;
	}

	int berekenScoreThreeOfAKindOfCarre(Worp w, int aantalGelijken) {
		int aantalEnen, aantalTweeen, aantalDrieen, aantalVieren, aantalVijven, aantalZessen;
		aantalEnen = aantalTweeen = aantalDrieen = aantalVieren = aantalVijven = aantalZessen = 0;
		berekenSomScore(w);
		score = 0;

		for (int i = 0; i < w.worpArray.length; i++) {
			switch (w.worpArray[i]) {
			case 1:
				aantalEnen++;
				if (aantalEnen == aantalGelijken) {
					score = somScore;
				}
				break;
			case 2:
				aantalTweeen++;
				if (aantalTweeen == aantalGelijken) {
					score = somScore;
				}
				break;
			case 3:
				aantalDrieen++;
				if (aantalDrieen == aantalGelijken) {
					score = somScore;
				}
				break;
			case 4:
				aantalVieren++;
				if (aantalVieren == aantalGelijken) {
					score = somScore;
				}
				break;
			case 5:
				aantalVijven++;
				if (aantalVijven == aantalGelijken) {
					score = somScore;
				}
				break;
			case 6:
				aantalZessen++;
				if (aantalZessen == aantalGelijken) {
					score = somScore;
				}
				break;
			}

		}
		return score;
	}

	int berekenScoreYathzee(Worp w) {
		score = 0;
		int vergelijkGetal = w.worpArray[0];
		if (w.worpArray[1] == vergelijkGetal && w.worpArray[2] == vergelijkGetal && w.worpArray[3] == vergelijkGetal
				&& w.worpArray[4] == vergelijkGetal) {
			score = 50;
		} else {
			score = 0;
		}
		return score;
	}

	int berekenScoreChance(Worp w) {
		score = berekenSomScore(w);
		return score;
	}

	boolean checkGroteStraat(int[] array) {
		boolean isGroteStraat = true;
		Arrays.sort(array);
		int vergelijkGetal = array[0];
		for (int i = 0; i < array.length; i++) {
			if (vergelijkGetal == array[i]) {
				vergelijkGetal++;
			} else {
				isGroteStraat = false;
			}
		}
		return isGroteStraat;
	}

	int berekenScoreStraat(Worp w, int keuze) {
		score = 0;
		switch (keuze) {
		case 1:
			if (checkGroteStraat(w.worpArray) == true) {
				score = 40;
			}
			break;
		case 2:
			if (checkKleineStraat(w.worpArray) == true) {
				score = 30;
			}
			break;
		default:
			score = 0;
		}
		return score;
	}

	boolean checkKleineStraat(int[] array) {
		boolean isKleineStraat = false;
		Arrays.sort(array);
		ArrayList<Integer> copyOfArray = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (i != array.length - 1 && array[i] != array[i + 1]) {
				Integer x = new Integer(array[i]);
				copyOfArray.add(x);
			} else if (i == array.length - 1) {
				Integer x = new Integer(array[i]);
				copyOfArray.add(x);
			}
		}
		String s;
		String cijferreeks = "";
		for (int i = 0; i < copyOfArray.size(); i++) {
			s = Integer.toString(copyOfArray.get(i));
			cijferreeks = cijferreeks + s;
		}
		if (cijferreeks.contains("1234") || cijferreeks.contains("2345") || cijferreeks.contains("3456")) {
			isKleineStraat = true;
		}
		return isKleineStraat;
	}

	void printScoreKaart() {
		System.out.println("Scorekaart:  ");
		for (int i = 0; i < kaart.length; i++) {
			System.out.print(kaart[i] + "\t");
		}
		System.out.println();
	}
	
	int berekenTotaalScore() {
		int somScoreKaart =0;
		int counter =0;
		for (int i = 0; i < kaart.length; i++) {
			somScoreKaart = somScoreKaart + kaart[i];
			if (kaart[i] == -1) {
				counter++;
			}
			totaalScore = somScoreKaart + counter;
		}
		return totaalScore;
	}

}
