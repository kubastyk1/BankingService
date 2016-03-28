import java.util.*;

public class ClientAccount {

	private String name, lastName, pesel;
	private static float money = 0;
	private static int accountNumber = 0, counter = 0;
	private WritingReadingTxt ioAccount = new WritingReadingTxt();
	public void createAccount(){
		
		Scanner scr = new Scanner(System.in);
		do{
			System.out.println("Podaj imiê: ");
			name = scr.nextLine();
		}while(!name.matches("[a-zA-Z]+"));
		
		do{
			System.out.println("Podaj nazwisko: ");
			lastName = scr.nextLine();
		}while(!lastName.matches("[a-zA-Z]+"));
		
		do{
			System.out.println("Podaj PESEL: ");
			pesel = scr.nextLine();
		}while(!pesel.matches("[0-9]+") /*&& pesel.length() == 11*/);
		accountNumber += 1;
		System.out.println(accountNumber + " :::: Imie: " + name + " Nazwisko: " + lastName + " PESEL: " + pesel);
		counter = 1;
		ioAccount.saveInTxt(name, lastName, pesel, money, accountNumber);
	}
	
	public void paymentOnAccount(){
		
		String[] table = ioAccount.chooseClient();
		float oldMoney = Float.parseFloat(table[4]);
		Scanner scr = new Scanner(System.in);
		System.out.println("Stan konta wynosi: " + oldMoney + " $ ");
		System.out.println("Podaj kwotê do wp³aty: ");
		money = oldMoney + scr.nextFloat();
		System.out.println("Stan konta wynosi: " + money + " $ ");
		ioAccount.writePayment(Integer.parseInt(table[0]), oldMoney, money);
		
	}
	
	public void paymentOffAccount(){
		
		Scanner scr = new Scanner(System.in);
		float sum = 0;
		System.out.println("Stan konta wynosi: " + money + " $ ");
		System.out.println("Podaj kwotê do wyp³aty: ");
		sum = scr.nextFloat();
		if(money < sum){
			System.out.println("Niewystarczaj¹ca iloœæ pieniêdzy. Wpisz 0 by wyjœæ.");
			paymentOffAccount();
		}else{
			money -= sum;
			System.out.println("Stan konta wynosi: " + money + " $ ");
		}
	}
	
	public boolean accountExist(){
		
		boolean exist;
		if(counter == 1)
			exist = true;
		else
			exist = false;
		return exist;
	}
	
}
