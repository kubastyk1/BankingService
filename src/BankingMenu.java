import java.io.FileNotFoundException;
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
			System.out.println("4. Wybór klienta ");
			System.out.println("5. Wyœwietl wszystkich klientów  ");
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
		
	}
	
	public void chooseAnOption(int g) {
		
		ClientAccount account = new  ClientAccount();
		WritingReadingTxt ioAccount = new WritingReadingTxt();
		WritingReadingExcel excel = new WritingReadingExcel();
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
		case 8: excel.updateExcel();
				break;
		default: System.out.println("Koniec programu");
				System.exit(1);
				break;
		}
		System.out.println("Wybierz polecenie: ");
	}
}
