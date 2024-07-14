package Utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class FormDataProvider {
		
	
	ExcelData exl = new ExcelData(".//ExcelFiles//FinalSheet.xlsx");
	
	@DataProvider(name="FormData")
	public String[][] getFormData() throws IOException
	{
		
		String[][] str = new String[11][11];
		
		for(int r=1; r<=11; r++)
		{
			for(int c=0; c<11; c++)
			{
				str[r-1][c] = exl.getCellData("Sheet1",r,c);
			}
		}
		
		return str;
	}
	
}
