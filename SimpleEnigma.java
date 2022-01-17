/**
* Klasse SimpleEnigma
*/
public class  SimpleEnigma {
		
	private int a;
	private Letterwheel[] wheels;
	private char[] output = new char[4];
	
	/**
	* Konstruktor SimpleEnigma
	* Bekommt Anzahl der Räder übergeben.
	* Überprüft, ob alle Letterwheels in verschiedenen Konfigurationen stehen.
	* Falls nicht, rotiert die.
	* @param anzahl = Anzahl von Räder
	*/
	public SimpleEnigma(int anzahl) {
		this.a = anzahl;
		this.wheels = new Letterwheel[a];
		
		for (int c = 0; c < a; c++) {
			this.wheels[c] = new Letterwheel();
		}
		boolean notEqual = false;
		if (a > 1) {
			loop:
			while (notEqual == false) {
				for (int n = 0; n < a - 1; n++) {
					for (int m = 1; m < a; m++) {
						if (n != m) {
							if ((this.wheels[n].equals(this.wheels[m])) == true) {
								this.wheels[n].rotate();
								continue loop;
							} else {
								notEqual = true;
							}	
						}
					}
				}
			}
		}
	}
	/**
	* Diese Funktion dreht die Räder.
	* Das erste Rad wird bei jedem Aufruf gedreht, 
	* das zweite bei jedem zweiten und so weiter.
	* @param y = momentaner Aufruf
	*/
	public void turnWheels(int y) {
		int raeder = this.a;
		int buchstabe = y + 1;
		for (int rad = 1; rad < raeder + 1; rad++) {
			if ((buchstabe % rad) == 0) {
				this.wheels[rad - 1].rotate();
			}
		}
	}
	/**
	* Erhält ein char Array von bis zu 4 Zeichen.
	* Die Buchstaben werden nacheinander von den Letterwheels verarbeitet. 
	* Die Ausgabe des vorherigen Letterwheels als Eingabe für das Nächste dient. 
	* Nachdem ein Charakter alle Letterwheels durchlaufen hat, 
	* wird die Funktion turnWheels aufgerufen. 
	* Wenn alle Buchstaben des Arrays verarbeitet worden sind und nicht "RING" ergeben, 
	* gibt die Funktion false zurück.
	* @param word = Eingangskombination von Buchstaben
	* @return true falls Ausgang RING ergibt,
	* und false falls nicht
	*/
	public boolean receive(char[] word)  {
		for (int y = 0; y < 4; y++) {
			char letter = word[y];
			for (int x = 0; x < a; x++) {
				letter = wheels[x].process(letter);
			}
			this.output[y] = letter;
			this.turnWheels(y);
		}
		String str = String.valueOf(output);
		if (str.equals("RING")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	* Diese Funktion gibt einen Hinweis über die Konfiguration aller Räder zurück.
	* @return string = Konfiguration aller Räder
	*/
	public String[] hint() {
		String[] string = new String[a];
		for (int z = 0; z < a; z++) {
			string[z] = "Wheel " + (z + 1) + ": " + this.wheels[z].toString();
		} 
		return string;
	}
	/**
	* Diese Funktion gibt einen Hinweis für das durch den int spezifizierte Rad zurück.
	* @param i = Nummer von Rad
	* @return spezifizierte Rad als String
	*/
	public String hint(int i) {
		return "Wheel " + i + ": " + wheels[i - 1].toString();
	}
	/**
	* Gibt die Ausgangskombination von Buchstaben, nachdem die durch alle Wheels verarbeitet worden sind.
	* @return output = char Array von Grösse vier
	*/	
	public char[] getOutput() {
		return this.output;
	}
}