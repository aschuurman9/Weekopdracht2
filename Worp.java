package weekopdracht2;

import java.util.ArrayList;

public class Worp {
	int[] worpArray = new int[5];

	int[] WorpUitslag(ArrayList<Dobbelsteen> rijdobbelstenen) {
		System.out.println("WORP");
		System.out.println("1 2 3 4 5");
		
		for (Dobbelsteen ds : rijdobbelstenen) {
			System.out.print(ds.ogen + " ");
		}
		System.out.println();
		return worpArray;

	}

	
}
