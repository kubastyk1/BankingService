import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


public class WritingReadingExcel {

	public void createWorkbook(){
		Workbook wb = new HSSFWorkbook();
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("f:\\workbook8.xls");
			wb.write(fileOut);
		   // fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
