package weekopdracht2;

import java.util.ArrayList;
import java.util.Arrays;

public class Scoreblad {

	int score;
	private int somScore;
	private int scoreEnen, scoreTweeen, scoreDrieen, scoreVieren, scoreVijven, scoreZessen, scoreThreeOfAKind, scoreCarre, scoreFullHouse,
			scoreKleineStraat, scoreGroteStraat, scoreYathzee, scoreChance;
	int[] kaart = { scoreEnen, scoreTweeen, scoreDrieen, scoreVieren, scoreVijven, scoreZessen, scoreThreeOfAKind,
			scoreCarre, scoreFullHouse, scoreKleineStraat, scoreGroteStraat, scoreYathzee, scoreChance };
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

	int berekenScoreEnenEnZessen(Worp w, int ogenGegooid) {
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

	int berekenScoreFullHouse(Worp w) {
		score = 0;
		Arrays.sort(w.worpArray);
		if (w.worpArray[0] == w.worpArray[1] && w.worpArray[3] == w.worpArray[4] && (w.worpArray[2] == w.worpArray[1] || w.worpArray[2] == w.worpArray[3])) {
			score = 25;
		}
		return score;
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
	
	void printScoreKaart() {
		System.out.println("Scorekaart:  ");
		for (int i = 0; i < kaart.length; i++) {
			if(kaart[i] == -1) {
				System.out.print("X" + "\t");
			} else {
				System.out.print(kaart[i] + "\t");
			}
		}
		System.out.println();
	}

}
