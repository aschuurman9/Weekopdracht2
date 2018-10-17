package weekopdracht2;

import java.util.ArrayList;

public class Speler {
	int[] worpGeschiedenis = new int[100];
	static int counter;
	static ArrayList<Speler> spelers = new ArrayList<>();
	String naam;
	
	Speler(String naam){
		this.naam = naam; 
	}
	
	void toevoegenAanWorpGeschiedenis(Worp w) {
		int[] worp = w.worpArray;
		for(int i= 0; i < worp.length; i++) {
			worpGeschiedenis[counter] = worp[i];
			counter++;
		}
		return;
	}
	
	void printWorpGeschiedenis() {
		System.out.println("Worpgeschiedenis: ");
		for(int i = 0; i < worpGeschiedenis.length; i++) {
			if (worpGeschiedenis[i] != 0) {
				System.out.print(worpGeschiedenis[i] + " ");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	void printSpelers() {
		for(int i = 0; i < spelers.size(); i++) {
		System.out.println(spelers.get(0));
		}
	}

}
	



