import java.io.File;
import java.util.ArrayList;




/**
 * @author meng
 *
 */
public class ResultGenerator {
	private FileParser fileParser;
	private ArrayList<String> dayList;
	private ArrayList<String> timeList;
	private ArrayList<String> sectionList;
	
	
	/**
	 * @param fileParser : FileParser object which contains information of each lab
	 */
	public ResultGenerator(FileParser fileParser) {
		super();
		this.fileParser = fileParser;
		
		//initialize information list for each lab
		this.dayList=fileParser.getDayList();
		this.timeList=fileParser.getTimeList();
		this.sectionList=fileParser.getSectionList();
		
	}
	
	/**
	 * @param outputFile: path of the csv output file
	 * write result to the outputFile 
	 */
	public void outputGenerator(File outputFile){
		
		
		
		
	}
	
	
	
	
	
}
