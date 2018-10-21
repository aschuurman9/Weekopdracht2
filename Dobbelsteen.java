package weekopdracht2;

import java.util.Random;

public class Dobbelsteen {
	int ogen;
	
	int werpen() {
		Random r = new Random();
		int worp = 1 + r.nextInt(6);
		return worp;
	}
}
