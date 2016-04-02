import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class WritingReadingExcel {

	private static final String filename = "f:\\workbook5.xls";
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
		
		try {
		     
		    FileInputStream file = new FileInputStream(new File(filename));
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
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
			
			
			/*Map<String, Object[]> data = new HashMap<String, Object[]>();
			data.put("1", new Object[] {"Emp No.", "Name", "Salary"});
			data.put("2", new Object[] {1d, "John", 1500000d});
			data.put("3", new Object[] {2d, "Sam", 800000d});
			data.put("4", new Object[] {3d, "Dean", 700000d});
			 
			Set<String> keyset = data.keySet();
			int rownum = 0;
			for (String key : keyset) {
			    Row row = sheet.createRow(rownum++);
			    Object [] objArr = data.get(key);
			    int cellnum = 0;
			    for (Object obj : objArr) {
			        Cell cell = row.createCell(cellnum++);
			        if(obj instanceof Date) 
			            cell.setCellValue((Date)obj);
			        else if(obj instanceof Boolean)
			            cell.setCellValue((Boolean)obj);
			        else if(obj instanceof String)
			            cell.setCellValue((String)obj);
			        else if(obj instanceof Double)
			            cell.setCellValue((Double)obj);
			    }
			}*/
			 
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
			
		    //Update the value of cell
		    /*cell = sheet.getRow(1).getCell(2);
		    cell.setCellValue(cell.getNumericCellValue() * 2);
		    cell = sheet.getRow(2).getCell(2);
		    cell.setCellValue(cell.getNumericCellValue() * 2);
		    cell = sheet.getRow(3).getCell(2);
		    cell.setCellValue(cell.getNumericCellValue() * 2);
		     
		    file.close();
		     */
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
		cell.setCellValue("Nr.konta /t/t");
		cell = row.createCell(1);
		cell.setCellValue("Imie /t/t");
		cell = row.createCell(2);
		cell.setCellValue("Nazwisko /t/t");
		cell = row.createCell(3);
		cell.setCellValue("PESEL /t/t");
		cell = row.createCell(4);
		cell.setCellValue("Stan konta /t/t");
		
		saveInExcel(workbook);
	}
	
	public Object[] chooseClient(){
		
		Object[] strtab = new Object[argnumber];
		try {
		    FileInputStream file = new FileInputStream(new File(filename));
		 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);
		    
		    readExcel();
		    Scanner scr = new Scanner(System.in);
			System.out.println("Podaj numer klienta: ");
			int clientNumber = scr.nextInt();
				
			Cell cell = sheet.getRow(clientNumber).getCell(0);
			double d = cell.getNumericCellValue();
			
			if(d == clientNumber){
				for(int i = 0; i < argnumber; i++){
					cell = sheet.getRow(clientNumber).getCell(i);
				
					switch(cell.getCellType()) {
	                case Cell.CELL_TYPE_BOOLEAN:
	                    //System.out.print(cell.getBooleanCellValue() + "\t");
	                    strtab[i] = cell.getBooleanCellValue();
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    //System.out.print(cell.getNumericCellValue() + "\t");
	                    strtab[i] = cell.getNumericCellValue();
	                    break;
	                case Cell.CELL_TYPE_STRING:
	                    //System.out.print(cell.getStringCellValue() + "\t");
	                    strtab[i] = cell.getStringCellValue();
	                    break;
					}
					//System.out.println();
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
	
