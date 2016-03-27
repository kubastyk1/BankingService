import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class WritingReadingTxt {

	String filePath = "f:\\myfile.txt";
	
	public void showClients(){
		
		try {
			String[] table = new String[5];
			int i = 0;
		    FileReader fileReader = new FileReader(filePath);
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    String textLine = bufferedReader.readLine();
		    do {
		    	if(i != 5){
		    		table[i] = textLine;
		    		textLine = bufferedReader.readLine();
		    		i++;
		    	}else {
		    		for(i = 0; i < 5; i++)
		    			System.out.print(table[i] + " - ");
		    		System.out.println();
		    		i = 0;
		    	}
		    } while (textLine != null || i == 5);
		    
		}catch (IOException e){
			e.printStackTrace();
		  } /*finally {
		    bufferedReader.close();
		  }*/
	}
	
	public void readFile() {
		
		int i = 0;
		showClients();
		
		Scanner scr = new Scanner(System.in);
		System.out.println("Podaj numer klienta: ");
		int clientNumber = scr.nextInt();
		
		try {
			String[] table = new String[5];
		    FileReader fileReader = new FileReader(filePath);
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    String textLine = bufferedReader.readLine();
		    for(i = 0; i < ((clientNumber - 1) * 5) - 1; i++ ){
		    	bufferedReader.readLine();
		    } 
		    i = 0;
		    do{
		    	table[i] = bufferedReader.readLine();
		    	i++;
		    }while(i != 5);
		    
		    for(i = 0; i < 5; i++)
    			System.out.print(table[i] + " - ");
		    System.out.println();
		    
		}catch (IOException e){
			e.printStackTrace();
		  } /*finally {
		    bufferedReader.close();
		  }*/
	   }
}
