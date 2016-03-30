import java.util.*;

public class ClientAccount {

	private String name, lastname, pesel;
	private static float money = 0;
	private static int accountnumber = 0;
	private WritingReadingTxt ioAccount = new WritingReadingTxt();
	private WritingReadingExcel excel = new WritingReadingExcel();
	
	public void createAccount(){
		
		Scanner scr = new Scanner(System.in);
		do{
			System.out.println("Podaj imi�: ");
			name = scr.nextLine();
		}while(!name.matches("[a-zA-Z]+"));
		
		do{
			System.out.println("Podaj nazwisko: ");
			lastname = scr.nextLine();
		}while(!lastname.matches("[a-zA-Z]+"));
		
		do{
			System.out.println("Podaj PESEL: ");
			pesel = scr.nextLine();
		}while(!pesel.matches("[0-9]+") /*&& pesel.length() == 11*/);
		accountnumber = ioAccount.getAccountNumber() + 1;
		System.out.println(accountnumber + " :::: Imie: " + name + " Nazwisko: " + lastname + " PESEL: " + pesel);
		//ioAccount.saveInTxt(name, lastName, pesel, money, accountNumber);
		String[] strtab = {Integer.toString(accountnumber), name, lastname, pesel, Float.toString(money)};
		excel.writeExcel(strtab);
	}
	
	public void paymentOnAccount(){
		
		String[] table = ioAccount.chooseClient();
		float oldMoney = Float.parseFloat(table[4]);
		Scanner scr = new Scanner(System.in);
		System.out.println("Stan konta wynosi: " + oldMoney + " $ ");
		System.out.println("Podaj kwot� do wp�aty: ");
		try{
			money = oldMoney + scr.nextFloat();
		} catch(InputMismatchException e){
			e.printStackTrace();
		}
		System.out.println("Stan konta wynosi: " + money + " $ ");
		ioAccount.writePayment(Integer.parseInt(table[0]), oldMoney, money);
		excel.updateExcel();
		
	}
	
	public void paymentOffAccount(){
		
		String[] table = ioAccount.chooseClient();
		float oldMoney = Float.parseFloat(table[4]);
		float sum = 0;
		Scanner scr = new Scanner(System.in);
		System.out.println("Stan konta wynosi: " + money + " $ ");
		System.out.println("Podaj kwot� do wyp�aty: ");
		try{
			sum = scr.nextFloat();
		} catch(InputMismatchException e){
			e.printStackTrace();
		}
		if(oldMoney < sum){
			System.out.println("Niewystarczaj�ca ilo�� pieni�dzy. Wpisz 0 by wyj��.");
			paymentOffAccount();
		}else{
			money = oldMoney - sum;
			System.out.println("Stan konta wynosi: " + money + " $ ");
		}
		ioAccount.writePayment(Integer.parseInt(table[0]), oldMoney, money);
	}
	
	
}
