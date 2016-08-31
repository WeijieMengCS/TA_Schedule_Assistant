import java.util.ArrayList;

public class Application {
    static String USAGE = "usage: Application filepath";

    public static void main(String[] args) {
        /*if (args.length < 1){
            System.out.println(USAGE);
            System.exit(1);
        }*/
        //String filePath = args[0];

        // Test use
        String filePath = "/Users/mengxueluo/Desktop/Fun/TA_Schedule/SampleData/fall16.csv";
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
    }
}
