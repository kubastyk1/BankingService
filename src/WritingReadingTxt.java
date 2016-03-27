import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class WritingReadingTxt {

	private String filePath = "f:\\myfile.txt";
	private static final int argNumber = 5; 		//Number of data arguments in File like (name, lastName, ...) 
	private String[] table = new String[argNumber];
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
	
	public void chooseClient() {
		
		showClients();
		Scanner scr = new Scanner(System.in);
		System.out.println("Podaj numer klienta: ");
		int clientNumber = scr.nextInt();
		int numberOfLine = (clientNumber - 1) * argNumber;
		BufferedReader bufferedReader = initialize();
		
		try {
		    for(i = 0; i < numberOfLine; i++ )
		    	bufferedReader.readLine();
		    i = 0;
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
	   }
}
