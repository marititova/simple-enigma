import java.util.Random;
/**
* Klasse Letterwheel
*/
public class Letterwheel {

	private char[] radInput = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
							   'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private char[] rad = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
						  'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	/**
	* Konstruktor Letterwheel
	* Erzeugt eine Randomzahl von 1 bis 25
	* und verschiebt rad so viel mal
	*/
	public Letterwheel() {
		Random random = new Random();
		int j = (random.nextInt(25) + 1);
		for (int r = 0; r < j; r++) {
			this.rotate();
		}
	}
	/**
	* Nimmt einen char entgegen 
	* und gibt den substituierten Buchstaben zurück.
	* @param letter = eingegebener
	* @return rad[i] = substituierter Buchstaben
	*/				
	public char process(char letter) {
		for (int i = 0; i < 26; i++) {
			if (radInput[i] == letter) {
				return rad[i];
			}
		}
		return '0';
	}
	/**
	* Rotiert das Rad um genau eine Stelle.
	*/
	public void rotate() {
		char zero = this.rad[0];
		for (int i = 0; i < 25; i++) {
			this.rad[i] = this.rad[i + 1];
		}
		this.rad[25] = zero;
	}
	/**
	* Gibt die momentane Konfiguration vom Rad als String zurück.
	* @return st = Konfiguration vom Rad
	*/
	public String toString() {
		String st = "";
		for (int i = 0; i < 26; i++) {
			st = st + rad[i];
		}
		return st;
	}
	/**
	* Prüft ob zwei Letterwheels in der selben Konfiguration stehen.
	* @param object = übergebene Objekt
	* @return true falls die Räder gleich sind
	* und false, falls unterschiedlich
	*/
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if ((object == null) || (getClass() != object.getClass())) {
			return false;
		} 
		Letterwheel lw = (Letterwheel) object;
		if (this.rad[0] == lw.rad[0]) {
			return true;
		} else {
			return false;
		}
	}	
}