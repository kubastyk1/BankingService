import java.util.Scanner;


public class BankingMenu {

	public void mainMenu(){
		
	
		
		int g = 0;
		Scanner odczyt = new Scanner(System.in);
		String s1 = " ";
		System.out.println("Witaj w systemie bankowym: ");
		
			System.out.println("");
			System.out.println("1. Za³u¿ konto ");
			System.out.println("2. Dokonaj wp³aty ");
			System.out.println("3. Dokonaj wyp³aty ");
			System.out.println("4. Wybierz klienta ");
			System.out.println("4. Transfer pieniêdzy ");
			System.out.println("5. Informacja o kliencje ");
			System.out.println("6. Informacja o wszystkich ");
			System.out.println("-----------");
			System.out.println("9. Zakoñcz");
			System.out.println("Wybierz polecenie: ");
		do{
			try {
				s1 = odczyt.nextLine();
				g = Integer.parseInt(s1);
			}catch (NumberFormatException e){
				System.out.println("Mozesz wprowadzic tylko liczby! ");
			}
			chooseAnOption(g);
		}while(!s1.equalsIgnoreCase("close"));
		//odczyt.close();
	}
	
	public void chooseAnOption(int g){
		
		ClientAccount account = new  ClientAccount();
		switch(g) {
		case 1: account.createAccount();
				break;
		case 2: account.paymentOnAccount();
				break;
		case 3: account.paymentOffAccount();
				break;
		case 4: account.readFile();
				break;
		default: System.out.println("Koniec programu");
				System.exit(1);
				break;
		}
		System.out.println("Wybierz polecenie: ");
	}
}
