/*********************************************************************
 *                           @author: Mx
 *********************************************************************/

import java.io.File;
import java.util.ArrayList;

public class Application {

    /**
     * Get stats from parsed file and assigned tas to each Section
     * And finally write the assigned result to a file
     * @param filePath: file path for the parse file
     * @param fileParser: parser used to parse the original file
     */
    public void run(String filePath, FileParser fileParser){
        //String filePath = "/Users/mengxueluo/Desktop/Fun/TA_Schedule/SampleData/fall16.csv";

        // Parse Data from the file
        CSVFileReader fileReader = new CSVFileReader();
        ArrayList<ArrayList<String>> data = fileReader.readFile(filePath);
        ArrayList<String> nameList = fileReader.getNameList();

        // Sort out the statistics from data
        StatsCollector collector = new StatsCollector();
        collector.setNameList(nameList);
        collector.setData(data);
        collector.collectStats();

        // Begin assignment
        StatsOperator operator = new StatsOperator();
        operator.setLabSectionList(collector.getLabSectionList());
        operator.setTAList(collector.getTAList());
        operator.setNumberOfParticipantsRequiredForEachSection(2);
        operator.beginAssignment();
        
        // Generate output
        ResultGenerator generator = new ResultGenerator(fileParser, operator);
        File output = new File("finalResult.csv");
        generator.outputGenerator(output);

    }
}
