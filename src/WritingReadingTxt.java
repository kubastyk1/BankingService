import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class WritingReadingTxt {

	private String filePath = "f:\\myfile.txt";
	private static final int argNumber = 5; 		//Number of data arguments in File like (name, lastName, ...) 
	private String[] table = new String[argNumber];
	private List<String> clients;
	private Path file = Paths.get("f:\\myfile.txt");
	private int i = 0;
	
	private BufferedReader initialize() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
			BufferedReader bufferedReader = null;
			return bufferedReader;
		}
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    return bufferedReader;
	}
	
	private void printingInformation(){
		
		for(i = 0; i < argNumber; i++)
			System.out.print(table[i] + " - ");
	    System.out.println();
	}
	
	private BufferedReader goToLine(BufferedReader bufferedReader, int lineNumber){
		
		for(i = 0; i < lineNumber; i++ )
			try {
				bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return bufferedReader;
	}
	
	private int howManyLines(){
		
		int count = 0;
		BufferedReader bufferedReader = initialize();
		try {
			while (bufferedReader.readLine() !=  null)
				count++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public void writePayment(int clientNumber, float oldMoney, float money){
		
		String line, input = "", fullInput = "", helper = "", helper2 = "";
		BufferedReader bufferedReader = initialize();
		int lineNumber = ((clientNumber - 1) * argNumber) + 4;
		try{
			for(i = 0; i < lineNumber; i++ ){
				line = bufferedReader.readLine();
				input += line +  System.lineSeparator();
			}
			helper = bufferedReader.readLine();		
			helper = helper.replace(Float.toString(oldMoney), Float.toString(money));		
			helper2 = System.lineSeparator();
		    while ((line = bufferedReader.readLine()) != null)
		        helper2 += line + System.lineSeparator();
		    fullInput = input + helper + helper2;		
		    FileOutputStream os = new FileOutputStream(filePath);
			os.write(fullInput.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showClients(){
		
		try {
			BufferedReader bufferedReader = initialize();
		    String textLine = bufferedReader.readLine();
		    do {
		    	if(i != argNumber){
		    		table[i] = textLine;
		    		textLine = bufferedReader.readLine();
		    		i++;
		    	}else {
		    		printingInformation();
		    		i = 0;
		    	}
		    } while (textLine != null || i == argNumber);
		    
		}catch (IOException e){
			e.printStackTrace();
		  } /*finally {
		    bufferedReader.close();
		  }*/
	}
	
	public String[] chooseClient() {
		
		showClients();
		Scanner scr = new Scanner(System.in);
		int clientNumber = 0;
		System.out.println("Podaj numer klienta: ");
		try{
			clientNumber = scr.nextInt();
		} catch(InputMismatchException e){
			e.printStackTrace();
		}
		int numberOfLine = (clientNumber - 1) * argNumber;
		BufferedReader bufferedReader = initialize();

		try {	
			bufferedReader = goToLine(bufferedReader, numberOfLine);
			i=0;		//must be zero
		    do{
		    	table[i] = bufferedReader.readLine();
		    	i++;
		    }while(i != argNumber);
		    printingInformation();
		}catch (IOException e){
			e.printStackTrace();
		  } /*finally {
		    bufferedReader.close();
		  }*/
		
		return table;
	   }
	
	public int getAccountNumber(){
		
		int accountNumber = 0;
		BufferedReader bufferedReader = initialize();
		int numberOfLine = howManyLines() - argNumber;
		try{
			goToLine(bufferedReader, numberOfLine);       
			accountNumber = Integer.parseInt(bufferedReader.readLine());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return accountNumber;
	}
	
	public void saveInTxt(String name, String lastName, String pesel, float money, int accountNumber) {
		
		String my_money = Float.toString(money);
		String my_number = Integer.toString(accountNumber);
		clients = Arrays.asList(my_number, name, lastName, pesel, my_money);
		if(!Files.exists(file)){
			try {
			    Files.createFile(file);
			} catch (IOException x) {
			    System.err.format("createFile error: %s%n", x);
			}
		}
		
		try{
			BufferedReader bufferedReader = initialize();
			bufferedReader.readLine();
			Files.write(file, clients, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
