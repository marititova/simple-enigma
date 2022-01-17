import java.util.Scanner;
/**
* Klasse GameMaster
*/
public class GameMaster {
	/**
	* Diese Funktion bekommt die Anzahl der Räder übergeben, 
	* konstruiert die SimpleEnigma mit dieser Anzahl und führt runtime aus.
	* @param args = argument
	*/
	public static void main(String[] args) {
		int anzahl = 0;
		System.out.println("Program creates a simple enigma with the chosen amount of letterwheels.");
		System.out.println("Each letterwheel has a diffrent configuration.");
		System.out.println("You enter 4 capital letters. Each of them will be successively processed by enigma.");
		System.out.println("The output of previous wheel serves as an input of the next one.");
		System.out.println("The first wheel rotates after each letter. The second after every second letter and so on.");
		System.out.println("The game ends when the output is RING.");
		System.out.println("Type: 'hint' to see configuration of all wheels or");
		System.out.println("      'hint numberOfWheel' to see configuration of selected wheeel.");
		while (true) {
			System.out.print("Enter number of wheels: ");
			Scanner read = new Scanner(System.in);
			if (read.hasNextInt()) {
				anzahl = read.nextInt();
				if (anzahl > 0) {
					break;
				} else {
					System.out.println("Wrong input!");
				} 	
			} else {
				System.out.println("Wrong input!");
			}
			//read.close(); 
		}
		SimpleEnigma enigma = new SimpleEnigma(anzahl);
		runtime(enigma, anzahl);
	}
	/**
	* Der Nutzer kann entweder hint für eine Ausgabe der Konfiguration aller Räder, 
	* hint <nummer> für eine Ausgabe des Hinweises von einem Rad 
	* oder vier Großbuchstaben eingeben.
	* Empfangene String wird in ein char Array umgewandelt
	* und dann an receive der SimpleEnigma Instanz übergeben.
	* Wenn diese Funktion true zurück gibt, hat der Spieler gewonnen.
	* @param enigma = Instanz von SimpleEnigma
	* @param anzahl = Anzahl von Räder
	*/
	public static void runtime(SimpleEnigma enigma, int anzahl) {
		char[] arr = new char[4];
		boolean b = false;
		loop:
		while (b != true) {
			System.out.println("What do you want to do?");
			Scanner input = new Scanner(System.in);
			String eingabe = input.nextLine();
			int len = eingabe.length();
			
			
			if (eingabe.equals("hint")) {
				for (int i = 0; i < anzahl; i++) {
					System.out.println(enigma.hint()[i]);
				}
				continue;
			}
			if ((len > 5) && (eingabe.substring(4, 5).equals(" "))) {
				try {
					int inp = Integer.parseInt(eingabe.substring(5, len));
					if ((inp <= anzahl) && (inp > 0)) {
						System.out.println(enigma.hint(Integer.parseInt(eingabe.substring(5, len))));
						continue;
					} else {
						System.out.println("Wrong input! Amount of wheels is " + anzahl + ".");
						continue;
					}
				} catch (NumberFormatException e) {
					System.out.println("Wrong input!");
					continue;
				}
			} 
			if ((!eingabe.equals("hint")) && (len == 4)) {
				for (int i = 0; i < 4; i++) {
					if (Character.isLowerCase(eingabe.charAt(i))) {
						System.out.println("You can only enter capital letters!");
						continue loop;
					} else {
						arr[i] = eingabe.charAt(i);	
					}
				}
				System.out.print("Input: ");
				for (int i = 0; i < 4; i++) {
					System.out.print(arr[i]);
				}
				System.out.println();
				
				b = enigma.receive(arr);
				
				System.out.print("Output: ");
				System.out.print(enigma.getOutput());
				System.out.println();
				
				if (b == true) {
					System.out.print("You have beaten the machine!");
					break;
				}
				continue;
			}
			System.out.println("Wrong input!");
		}
	}
}