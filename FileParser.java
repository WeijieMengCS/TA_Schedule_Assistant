
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileParser {

	File sourceFile;
	public FileParser(File sourceFile) {
		super();
		this.sourceFile = sourceFile;
		
	}
	
	
	public void csvReader(){
		
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	        String line = "";
	        String cvsSplitBy = ",";

	        try {

	            br = new BufferedReader(new FileReader(this.sourceFile));
	            bw = new BufferedWriter(new FileWriter(new File("tempFile.csv")));
	            int counter = 0 ; 
	            while ((line = br.readLine()) != null) {
	            	
	                // use comma as separator
	                String[] content = line.split(cvsSplitBy);

	               // System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

	            }

	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

		
		
	}
	
 

}
