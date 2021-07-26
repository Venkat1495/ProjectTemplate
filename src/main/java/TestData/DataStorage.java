package TestData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataStorage {
	
	String testCasesNames = "Test_Cases";
	String path = System.getProperty("user.dir");
	//fileInputStream argument
	FileInputStream fis;
	XSSFWorkbook workbook;

	public String getData(String testcaseName, String className, String columnName) throws IOException
	{
		
		//ArrayList<String> a=new ArrayList<String>();
		String a = null;
		fis= new FileInputStream(path+"\\src\\main\\java\\TestData\\Automation Test Data.xlsx");
		workbook=new XSSFWorkbook(fis);
		int sheets=workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
		if(workbook.getSheetName(i).equalsIgnoreCase(className))
		{
		XSSFSheet sheet=workbook.getSheetAt(i);
		//Identify Testcases gloom by scanning the entire 1st row

		Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
		Row firstrow= rows.next();
		Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
		int k=0;
		int testCaseColoumn = 0;
		int testCaseDataColumn =0;
		while(ce.hasNext())
		{
			Cell value=ce.next();
			if(value.getStringCellValue().equalsIgnoreCase(testCasesNames))
			{
				testCaseColoumn=k;
			}
			if(value.getStringCellValue().equalsIgnoreCase(columnName))
			{
				testCaseDataColumn=k;
			}
		k++;
		}
		
		//once column is identified then scan entire testcase column to identify required testcase row
		
		while(rows.hasNext())
		{

		Row r=rows.next();
		if(r.getCell(testCaseColoumn).getStringCellValue().equalsIgnoreCase(testcaseName))
		{
		
		//after you grab required testcase row = 

		Iterator<Cell>  cv=r.cellIterator();
			while(cv.hasNext())
			{
			Cell c= cv.next();
				if(c.getColumnIndex() == testCaseDataColumn)
				if(c.getCellType()==CellType.STRING)
				{
				a = c.getStringCellValue();
				}
			else if(c.getCellType()==CellType.NUMERIC)
				{
				a = NumberToTextConverter.toText(c.getNumericCellValue());
				}
			}
		}

		}




		}
		}
		return a;

		
	}
	
	public String getDataArray(String testcaseName, String className, String columnName, int v) throws IOException
	{
		
		//ArrayList<String> a=new ArrayList<String>();
		String a = null;
		
		//fileInputStream argument
		fis=new FileInputStream(path+"\\src\\main\\java\\TestData\\Automation Test Data.xlsx");
		workbook=new XSSFWorkbook(fis);

		int sheets=workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
		if(workbook.getSheetName(i).equalsIgnoreCase(className))
		{
		XSSFSheet sheet=workbook.getSheetAt(i);
		//Identify Testcases gloom by scanning the entire 1st row

		Iterator<Row>  rows= sheet.iterator();// sheet is collection of rows
		Row firstrow= rows.next();
		Iterator<Cell> ce=firstrow.cellIterator();//row is collection of cells
		int k=0;
		int testCaseColoumn = 0;
		int testCaseDataColumn =0;
		while(ce.hasNext())
		{
			Cell value=ce.next();
			if(value.getStringCellValue().equalsIgnoreCase(testCasesNames))
			{
				testCaseColoumn=k;
			}
			if(value.getStringCellValue().equalsIgnoreCase(columnName))
			{
				testCaseDataColumn=k;
			}
		k++;
		}
		
		//once column is identified then scan entire testcase column to identify required testcase row
		int j =1;
		int g = 0;
		while(rows.hasNext())
		{

		Row r=rows.next();
		if(r.getCell(testCaseColoumn).getStringCellValue().equalsIgnoreCase(testcaseName))
		{
		g = g + 1;
		}
		if(g==v)
		{
			if(r.getCell(testCaseColoumn).getStringCellValue().equalsIgnoreCase(testcaseName))
			{
			
			//after you grab required testcase row = 

			Iterator<Cell>  cv=r.cellIterator();
				while(cv.hasNext())
				{
				Cell c= cv.next();
					if(c.getColumnIndex() == testCaseDataColumn)
					if(c.getCellType()==CellType.STRING)
					{
					a = c.getStringCellValue();
					}
				else if(c.getCellType()==CellType.NUMERIC)
					{
					a = NumberToTextConverter.toText(c.getNumericCellValue());
					}
				}
			}
		}

		j++;
		}



		}
		}
		return a;

		
	}


}


