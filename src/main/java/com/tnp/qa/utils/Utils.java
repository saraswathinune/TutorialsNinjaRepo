package com.tnp.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utils {

	public static final int IMPLICIT_WAIT_TIME= 30;
	public static final int PAGE_LOAD_TIME= 30;
	
	public static String  generateEmailWithTimeStamp()
	{
		Date date = new Date();
		String timestamp= date.toString().replace(" ","_").replace(":","_");
		return "test"+timestamp+"@gmail.com";
		
	}
	
	public static Object[][] readDatafromExcel(String sheetName)
	{
		File excelFile= new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"tnp"+File.separator+"qa"+File.separator+"testdata"+File.separator+"tnpTestData.xlsx");
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(excelFile);
			workbook= new XSSFWorkbook(fis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		XSSFSheet sheet= workbook.getSheet(sheetName);
		
		int rows=  sheet.getLastRowNum();
		int cols= sheet.getRow(0).getLastCellNum();
		
		Object[][] data= new Object[rows][cols];
		
		for(int i=1; i<rows; i++)
		{
			XSSFRow row= sheet.getRow(i);
			for(int j=0; j<cols;j++)
			{
				
				XSSFCell cell=row.getCell(j);
				CellType celltype=cell.getCellType();
				
				switch(celltype) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
			
		}
		return data;
	}
	
	public static String captureScreenShot(WebDriver driver, String testName) {

		
			
		File srcScreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String srcScreenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(srcScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return srcScreenshotPath;
	}
}
