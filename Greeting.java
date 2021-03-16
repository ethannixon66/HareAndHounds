import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Greeting {
	
	public void displayGreeting() {
	
		BufferedReader txtReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("greeting.txt"),StandardCharsets.UTF_8));
		Scanner scan = new Scanner(txtReader);
		scan.useDelimiter("A");
		String greeting = scan.next();
		System.out.println(greeting);
		scan.close();
	
		Scanner pressEnter = new Scanner(System.in);
		pressEnter.nextLine();

	}
}