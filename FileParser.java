package TA_Schedule_Assistant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

	File sourceFile;
	private ArrayList<String> dayList;
	private ArrayList<String> timeList;
	private ArrayList<String> sectionList;
	public FileParser(File sourceFile) {
		super();
		this.sourceFile = sourceFile;
		this.dayList = new ArrayList<String>();
		this.sectionList= new ArrayList<String>();
		this.timeList = new ArrayList<String>();
	}
	
	
	public void csvReader(){
		
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        int flag =0;
	        try {

	            br = new BufferedReader(new FileReader(this.sourceFile));
	            bw = new BufferedWriter(new FileWriter(new File("tempFile.csv")));
	            int counter = 1 ,realLength=0; 
	            while ((line = br.readLine()) != null) {
	            	String outputLine= "";
	            	System.out.println(line);
	                // use comma as separator
	                String[] content = line.split(cvsSplitBy);
	                System.out.println(content.length);
	               /*for (int i = 0; i < content.length; i++) {
					System.out.print(content[i]+"   ");
				}*/
	               System.out.println();
	                if(flag ==0 ){
	                	realLength=content.length;
	                	outputLine+="TIME";
	                	for (int i = 3; i < content.length; i++) {
							outputLine=outputLine+","+content[i];
						}
	                	//System.out.println(outputLine);
	                	outputLine+="\n";
	                	bw.write(outputLine);
	                	flag=1;
	                }else{
	                	dayList.add(content[0]);
	                	timeList.add(content[1]);
	                	sectionList.add(content[2]);
	                	outputLine+=counter++;
	                	for (int i = 3; i < content.length; i++) {
							outputLine=outputLine+","+ (content[i].equals("")?"n":"y");
						}
	                	System.out.println("realLength = " +realLength);
	                	if(content.length!=realLength){
	                		for (int i = content.length; i < realLength; i++) {
								outputLine=outputLine+","+"n";
							}
	                		
	                	}
	                	outputLine+="\n";
	                	
	                	bw.write(outputLine);
	                }
	              
	            }
	            
	            //close stream 
	            bw.flush();
	            bw.close();
	            br.close();

	            
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
