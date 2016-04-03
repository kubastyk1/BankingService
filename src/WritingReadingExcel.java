import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class WritingReadingExcel {

	public final String filename = "f:\\workbook5.xls";
	private static final int argnumber = 5; 
	
	private void saveInExcel(HSSFWorkbook workbook){
		
		try {
		    FileOutputStream out = new FileOutputStream(new File(filename));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}	
	
	public void readExcel(){
		
		HSSFWorkbook workbook = null;
		try {
		     
		    FileInputStream file = new FileInputStream(new File(filename));
		    workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);

		    Iterator<Row> rowIterator = sheet.iterator();
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		             
		            Cell cell = cellIterator.next();
		             
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                    System.out.print(cell.getBooleanCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                    System.out.print(cell.getStringCellValue() + "\t\t");
		                    break;
		            }
		        }
		        System.out.println("");
		    }
		    file.close();
		    FileOutputStream out = 
		        new FileOutputStream(new File(filename));
		    workbook.write(out);
		    out.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void writeExcel(String[] strtab){
		
		try{
			FileInputStream file = new FileInputStream(new File(filename));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			int cellnum = 0;
			Row row = sheet.createRow(sheet.getLastRowNum() + 1);
			Cell cell = null;
			for(String str: strtab){
				
				cell = row.createCell(cellnum);
		        cell.setCellValue(str);
				cellnum++;
			}

			saveInExcel(workbook);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void updateExcel(Object[] table, double money){
		
		try {
		    FileInputStream file = new FileInputStream(new File(filename));
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);

			double rown = (double) table[0];
			int rr = (int) rown;
			Cell cell = sheet.getRow(rr).getCell(argnumber-1);
			cell.setCellValue(money);
		    saveInExcel(workbook);
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void createExcel(){
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("My sheet");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Nr.konta");
		cell = row.createCell(1);
		cell.setCellValue("Imie");
		cell = row.createCell(2);
		cell.setCellValue("Nazwisko");
		cell = row.createCell(3);
		cell.setCellValue("PESEL");
		cell = row.createCell(4);
		cell.setCellValue("Stan konta");
		
		saveInExcel(workbook);
	}
	
	public Object[] chooseClient(){
		
		Object[] strtab = new Object[argnumber];
		HSSFWorkbook workbook = null;
		try(Scanner scr = new Scanner(System.in)) {
		    FileInputStream file = new FileInputStream(new File(filename));
		 
		    workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);
		    
		    readExcel();
			System.out.println("Podaj numer klienta: ");
			int clientNumber = scr.nextInt();
				
			Cell cell = sheet.getRow(clientNumber).getCell(0);
			double d = cell.getNumericCellValue();
			
			if(d == clientNumber){
				for(int i = 0; i < argnumber; i++){
					cell = sheet.getRow(clientNumber).getCell(i);
				
					switch(cell.getCellType()) {
	                case Cell.CELL_TYPE_BOOLEAN:
	                    strtab[i] = cell.getBooleanCellValue();
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    strtab[i] = cell.getNumericCellValue();
	                    break;
	                case Cell.CELL_TYPE_STRING:
	                    strtab[i] = cell.getStringCellValue();
	                    break;
					}
				}	
			}else
				System.out.println("Nie ma takiego klienta ");
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
	 	  e.printStackTrace();
		}
		return strtab;
	}
}
	
