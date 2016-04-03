import java.io.File;
import java.util.Scanner;

public class BankingMenu {

	public void mainMenu(){
		
		int g = 0;
		Scanner odczyt = new Scanner(System.in);
		String s1 = " ";
		System.out.println("Witaj w systemie bankowym: ");
		
			System.out.println("");
			System.out.println("1. Zaluz konto ");
			System.out.println("2. Dokonaj wplaty ");
			System.out.println("3. Dokonaj wyplaty ");
			System.out.println("4. Wybor klienta ");
			System.out.println("5. Wyswietl wszystkich klientow  ");
			System.out.println("4. Transfer pieniedzy ");
			System.out.println("5. Informacja o kliencje ");
			System.out.println("6. Informacja o wszystkich ");
			System.out.println("-----------");
			System.out.println("9. Zakoncz");
			System.out.println("Wybierz polecenie: ");
		do{
			try {
				s1 = odczyt.nextLine();
				g = Integer.parseInt(s1);
			}catch (NumberFormatException e){
				System.out.println("Mozesz wprowadzic tylko liczby! ");
				mainMenu();
			}
			chooseAnOption(g);
		}while(!s1.equalsIgnoreCase("close"));
		odczyt.close();
	}
	
	public void chooseAnOption(int g) {
		
		ClientAccount account = new  ClientAccount();
		WritingReadingTxt ioAccount = new WritingReadingTxt();
		WritingReadingExcel excel = new WritingReadingExcel();
		
		File file = new File(excel.filename);						//Excel
		if(!file.exists()){
			excel.createExcel();
		}
		
		switch(g) {
		case 1: account.createAccount();
				break;
		case 2: account.paymentOnAccount();
				break;
		case 3: account.paymentOffAccount();
				break;
		case 4: ioAccount.chooseClient();
				break;
		case 5: ioAccount.showClients();
				break;
		case 6: excel.createExcel();
				break;
		case 7: excel.readExcel();
				break;
		default: System.out.println("Koniec programu");
				System.exit(1);
				break;
		}
		System.out.println("Wybierz polecenie: ");
	}
}
