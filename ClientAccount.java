import java.util.*;

public class ClientAccount {

	public String name, lastName, pesel, adres;
	public static float money = 0;
	public int accauntNumber;
	public static int counter = 0;
	
	public void createAccount(){
		
		Scanner scr = new Scanner(System.in);
		do{
			System.out.println("Podaj imi�: ");
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
		
		System.out.println("Imie: " + name + " Nazwisko: " + lastName + " PESEL: " + pesel);
		counter = 1;
		saveToTxt(name, lastname, pesel);
	}
	
	public void paymentOnAccount(){
		
		if(counter == 1){
			Scanner scr = new Scanner(System.in);
			System.out.println("Stan konta wynosi: " + money + " $ ");
			System.out.println("Podaj kwot� do wp�aty: ");
			money += scr.nextFloat();
			System.out.println("Stan konta wynosi: " + money + " $ ");
		}else
			System.out.println("Najpierw musisz za�o�y� konto ");
	}
	
	public void paymentOffAccount(){
		
		if(counter == 1){
			Scanner scr = new Scanner(System.in);
			float sum = 0;
			System.out.println("Stan konta wynosi: " + money + " $ ");
			System.out.println("Podaj kwot� do wyp�aty: ");
			sum = scr.nextFloat();
			if(money < sum){
				System.out.println("Niewystarczaj�ca ilo�� pieni�dzy. Wpisz 0 by wyj��.");
				paymentOffAccount();
			}else{
				money -= sum;
				System.out.println("Stan konta wynosi: " + money + " $ ");
			}
		}else
			System.out.println("Najpierw musisz za�o�y� konto ");
	}
	
	public void SaveToTxt(Strring name, String lastName, String pesel){
		
		List<String> lines = Arrays.asList(name, lastName, pesel);
		Path file = Paths.get("Clients.txt");
		Files.write(file, lines, Charset.forName("UTF-8"));
		System.out.println("Dokon�em zapisu ");
		//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
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
